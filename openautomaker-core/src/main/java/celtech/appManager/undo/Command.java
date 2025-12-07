package celtech.appManager.undo;

/**
 * A Command represents an atomic change that can be undone and redone. It can also be merged with a previous Command of the same type, if desired.
 * 
 */
public interface Command {

	/**
	 * Perform the command.
	 */
	public void do_();

	/**
	 * Undo the command.
	 */
	public void undo();

	/**
	 * Redo the command.
	 */
	public void redo();

	/**
	 * Can this command be merged with the given command?.
	 */
	public boolean canMergeWith(Command command);

	/**
	 * Merge the given command with this command. This command will remain in the stack, the other command will be deleted.
	 */
	public void merge(Command command);

}
