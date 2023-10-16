///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Password Cracking Program
// Course:          CS 300, Summer, 2023
//
// Author:          Max Liss-'s-Gravemade
// Email:           lisssgravema@wisc.edu
// Lecturer's Name: Michelle Jensen
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// https://canvas.wisc.edu/courses/355989/files/33238313?wrap=1
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

/**
 * Class to represent a binary search tree (BST) node that contains only
 * Password objects.
 *
 * @author Michelle & max liss
 */
public class PasswordNode {
	private Password password; // the password data this node stores
	private PasswordNode left; // a reference to node that is the left child
	private PasswordNode right; // a reference to the node that is the right child

	/**
	 * 1-argument constructor that sets the only data of the node.
	 *
	 * @param password the password for this node to store
	 *
	 * @author Michelle
	 */
	public PasswordNode(Password password) {
		this.password = password;
	}

	/**
	 * 3-argument constructor that sets all three data field
	 *
	 * @param password, password the password for this node to store
	 * @param left,     the reference to the node that is the left child
	 * @param right,    the reference to the node that is the right child
	 *
	 * @author Michelle
	 */
	public PasswordNode(Password password, PasswordNode left, PasswordNode right) {
		this(password);
		this.left = left;
		this.right = right;
	}

	/**
	 * Setter for left data field
	 *
	 * @param left, the reference to the node to be the left child
	 *
	 * @author Michelle
	 */
	public void setLeft(PasswordNode left) {
		this.left = left;
	}

	/**
	 * Setter for right data field
	 *
	 * @param right, the reference to the node to be the right child
	 *
	 * @author Michelle
	 */
	public void setRight(PasswordNode right) {
		this.right = right;
	}

	/**
	 * Getter for left data field
	 *
	 * @return the reference to the node that is the left child
	 *
	 * @author Michelle
	 */
	public PasswordNode getLeft() {
		return this.left;
	}

	/**
	 * Getter for right data field
	 *
	 * @return the reference to the node that is the right child
	 *
	 * @author Michelle
	 */
	public PasswordNode getRight() {
		return this.right;
	}

	/**
	 * Getter for password data field
	 *
	 * @return the password object that this node stores
	 *
	 * @author Michelle
	 */
	public Password getPassword() {
		return this.password;
	}

	/**
	 * Determines if the current node is a leaf node
	 *
	 * @return true if this node is a leaf, false otherwise
	 *
	 * @author max liss
	 */
	public boolean isLeafNode() {
		return this.left == null && this.right == null;
	}

	/**
	 * Determines if the current node has a right child
	 *
	 * @return true if this node has a right child, false otherwise
	 *
	 * @author max liss
	 */
	public boolean hasRightChild() {
		return this.right != null;
	}

	/**
	 * Determines if the current node has a left child
	 *
	 * @return true if this node has a left child, false otherwise
	 *
	 * @author max liss
	 */
	public boolean hasLeftChild() {
		return this.left != null;
	}

	/**
	 * Determines how many children nodes this node has. RECALL: Nodes in a binary
	 * tree can have AT MOST 2 children
	 *
	 * @return The number of children this node has
	 *
	 * @author max liss
	 */
	public int numberOfChildren() {
		int count = 0;
		if (this.left != null)
			count++;
		if (this.right != null)
			count++;
		return count;
	}

	/**
	 * Sets the password and the left child of this node.
	 *
	 * @param password the new password to set for this node
	 * @param left     the new left child node to set for this node
	 */
	public void setPasswordNode(Password password, PasswordNode left) {
		this.password = password;
		this.left = left;
	}
}
