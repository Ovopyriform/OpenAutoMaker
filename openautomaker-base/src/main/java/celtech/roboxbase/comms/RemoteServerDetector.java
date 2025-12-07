package celtech.roboxbase.comms;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openautomaker.base.comms.print_server.PrintServerConnection;
import org.openautomaker.base.inject.comms.PrintServerConnectionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 *
 * @author Ian
 */

//TODO: All of the old HttpURL stuff has to be removed.  Can't be dealing with the export nonsense
// Also, why doesn't this define the thread it runs on?  Why is it defined in a page controller?

@Singleton
public class RemoteServerDetector {

	private static final Logger LOGGER = LogManager.getLogger();

	private InetSocketAddress transmitGroup = null;
	private DatagramChannel datagramChannel = null;
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final int MAX_WAIT_TIME_MS = 2000;
	private static final int CYCLE_WAIT_TIME_MS = 200;

	private void setupDatagramChannel(NetworkInterface localInterface) {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Using local address " + localInterface.toString());

		try {
			InetSocketAddress multicastAddress = new InetSocketAddress(RemoteDiscovery.multicastAddress, RemoteDiscovery.remoteSocket);
			DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);

			datagramChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
			datagramChannel.bind(new InetSocketAddress(RemoteDiscovery.remoteSocket));
			datagramChannel.setOption(StandardSocketOptions.IP_MULTICAST_IF, localInterface);
			datagramChannel.configureBlocking(false);

			//Set the configured config
			transmitGroup = multicastAddress;
			this.datagramChannel = datagramChannel;

			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Setup datagram channel " + datagramChannel.toString());

		}
		catch (IOException ex) {
			LOGGER.debug("IO Exception when checking network interface : " + ex.getMessage());
		}
	}

	private final PrintServerConnectionFactory detectedServerFactory;

	@Inject
	protected RemoteServerDetector(
			PrintServerConnectionFactory printServerConnectionFactory) {

		this.detectedServerFactory = printServerConnectionFactory;

		Enumeration<NetworkInterface> networkInterfaces = null;

		try {
			networkInterfaces = NetworkInterface.getNetworkInterfaces();
		}
		catch (SocketException ex) {
			LOGGER.error("Socket Exception when getting network interfaces: ", ex);
		}

		if (networkInterfaces != null) {
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface ni = networkInterfaces.nextElement();
				Enumeration<InetAddress> nias = ni.getInetAddresses();

				while (nias.hasMoreElements()) {
					InetAddress ia = nias.nextElement();

					if (LOGGER.isDebugEnabled())
						LOGGER.debug("Checking internet address " + ia.toString());

					if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress()) {
						setupDatagramChannel(ni);
						break;
					}
				}

				// No need to check any more
				if (datagramChannel != null)
					break;
			}
		}

		// If we have a datagram channel, return;
		if (datagramChannel != null)
			return;

		//Try to default to the loopback if we can't enumerate anything from network interfaces
		try {
			NetworkInterface localInterface = NetworkInterface.getByInetAddress(InetAddress.getLoopbackAddress());

			if (localInterface != null)
				setupDatagramChannel(localInterface);
		}
		catch (SocketException ex) {
			LOGGER.debug("Socket exception when checking loopback address: " + ex.getMessage());
		}

		if (datagramChannel == null)
			LOGGER.error("Unable to set up remote discovery client");
	}

	//	private RemoteServerDetector() {
	//		boolean done = false;
	//		// Look for a local interface with external access. The code used to do the following:
	//		// 
	//		//     NetworkInterface localInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
	//		//
	//		// but this failed on some Linux distributions, because InetAddress.getLocalHost() would return 127.0.1.1.
	//		// This address did not map to a network interface, so localInterface was null.
	//		//
	//		// This code, copied from StackOverflow, finds the first interface that is not a loopback or link local address.
	//		try {
	//			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
	//			while (!done && networkInterfaces.hasMoreElements()) {
	//				try {
	//					NetworkInterface ni = networkInterfaces.nextElement();
	//					Enumeration<InetAddress> nias = ni.getInetAddresses();
	//					while (nias.hasMoreElements()) {
	//						InetAddress ia = nias.nextElement();
	//						LOGGER.debug("Checking internet address " + ia.toString());
	//						if (!ia.isLinkLocalAddress() &&
	//								!ia.isLoopbackAddress()) {
	//							setupDatagramChannel(ni);
	//							done = true;
	//							break;
	//						}
	//					}
	//				}
	//				catch (IOException ex) {
	//					LOGGER.debug("IO Exception when checking network interface : " + ex.getMessage());
	//				}
	//			}
	//		}
	//		catch (SocketException ex) {
	//			LOGGER.debug("Socket Exception when getting network interfaces : " + ex.getMessage());
	//		}
	//
	//		if (!done) {
	//			// No external interfaces found. Try the loopback address. Not sure this is actually useful.
	//			try {
	//				NetworkInterface localInterface = NetworkInterface.getByInetAddress(InetAddress.getLoopbackAddress());
	//				if (localInterface != null) {
	//					setupDatagramChannel(localInterface);
	//					done = true;
	//				}
	//			}
	//			catch (SocketException ex) {
	//				LOGGER.debug("Socket exception when checking loopback address: " + ex.getMessage());
	//			}
	//			catch (IOException ex) {
	//				LOGGER.debug("IO Exception when checking loopback address: " + ex.getMessage());
	//			}
	//		}
	//
	//		if (!done) {
	//			LOGGER.error("Unable to set up remote discovery client");
	//		}
	//	}

	//	public static RemoteServerDetector getInstance() {
	//
	//		return instance;
	//	}

	public List<PrintServerConnection> searchForServers() throws IOException {
		List<PrintServerConnection> discoveredServers = new ArrayList<>();

		ByteBuffer sendBuffer = ByteBuffer.wrap(RemoteDiscovery.discoverHostsMessage.getBytes("US-ASCII"));
		LOGGER.debug("Sending Ello?");
		datagramChannel.send(sendBuffer, transmitGroup);

		int waitTime = 0;
		while (waitTime < MAX_WAIT_TIME_MS) {
			ByteBuffer inputBuffer = ByteBuffer.allocate(100);
			InetSocketAddress inboundAddress = (InetSocketAddress) datagramChannel.receive(inputBuffer);
			if (inboundAddress != null) {
				byte[] inputBytes = new byte[100];
				int bytesRead = inputBuffer.position();
				inputBuffer.rewind();
				inputBuffer.get(inputBytes, 0, bytesRead);
				String receivedData = new String(Arrays.copyOf(inputBytes, bytesRead), "US-ASCII");

				if (receivedData.equals(RemoteDiscovery.iAmHereMessage)) {
					LOGGER.debug("searchForServers got response from address " + inboundAddress.getAddress());
					PrintServerConnection newServer = detectedServerFactory.create(inboundAddress.getAddress());
					if (newServer.whoAreYou()) {
						LOGGER.debug("Adding server " + inboundAddress.getAddress() + " to newly discovered server list.");
						discoveredServers.add(newServer);
					}
				}
				else if (receivedData.equals(RemoteDiscovery.discoverHostsMessage)) {
					// FIXME On Macs, it seems to receive the discoverHostsMessage (twice) as well as the iAmHereMessage.
					// Don't know why.
					LOGGER.debug("Received \"" + RemoteDiscovery.discoverHostsMessage + "\" from address " + inboundAddress.getAddress());
				}
				else {
					LOGGER.warn("Didn't understand the response from remote server with address " + inboundAddress.getAddress() + ". I saw: " + receivedData);
				}
			}
			else {
				try {
					Thread.sleep(CYCLE_WAIT_TIME_MS);
					waitTime += CYCLE_WAIT_TIME_MS;
				}
				catch (InterruptedException ex) {
					LOGGER.info("interrupted");
				}
			}
		}

		return discoveredServers;
	}
}
