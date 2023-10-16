
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
// https://canvas.wisc.edu/courses/355989/files/33238315?wrap=1
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////
import java.util.NoSuchElementException;

public class PasswordStorage {
	protected PasswordNode root; // the root of this BST that contains passwords
	private int size; // how many passwords are in the BST
	private final Attribute COMPARISON_CRITERIA; // what password information to use to determine order in the tree

	/**
	 * Constructor that creates an empty BST and sets the comparison criteria.
	 * 
	 * @param comparisonCriteria, the Attribute that will be used to determine order
	 *                            in the tree
	 */
	public PasswordStorage(Attribute comparisonCriteria) {
		this.root = null;
		this.size = 0;
		this.COMPARISON_CRITERIA = comparisonCriteria;
	}

	/**
	 * Getter for this BST's criteria for determining order in the three
	 * 
	 * @return the Attribute that is being used to make comparisons in the tree
	 */
	public Attribute getComparisonCriteria() {
		return this.COMPARISON_CRITERIA;
	}

	/**
	 * Getter for this BST's size.
	 * 
	 * @return the size of this tree
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Determines whether or not this tree is empty.
	 * 
	 * @return true if it is empty, false otherwise
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Provides in-order String representation of this BST, with each Password on
	 * its own line. The String representation ends with a newline character ('\n')
	 * 
	 * @return this BST as a string
	 */
	@Override
	public String toString() {
		return toStringHelper(this.root);
	}

	/**
	 * Recursive method the uses an in-order traversal to create the string
	 * representation of this tree.
	 * 
	 * @param currentNode, the root of the current tree
	 * @return the in-order String representation of the tree rooted at current node
	 */
	private String toStringHelper(PasswordNode currentNode) {
		if (currentNode == null) {
			return "";
		}
		String leftStr = toStringHelper(currentNode.getLeft());
		String currentStr = currentNode.getPassword().toString() + "\n";
		String rightStr = toStringHelper(currentNode.getRight());
		return leftStr + currentStr + rightStr;
	}

	/**
	 * Determines whether or not this tree is actually a valid BST.
	 * 
	 * @return true if it is a BST, false otherwise
	 */
	public boolean isValidBST() {
		return isValidBSTHelper(this.root, Password.getMinPassword(), Password.getMaxPassword());
	}

	/**
	 * Recursively determines if the the tree rooted at the current node is a valid
	 * BST. That is, every value to the left of currentNode is "less than" the value
	 * in currentNode and every value to the right of it is "greater than" it.
	 *
	 * @param currentNode, the root node of the current tree
	 * @param lowerBound,  the smallest possible password
	 * @param upperBound,  the largest possible password
	 * @return true if the tree rooted at currentNode is a BST, false otherwise
	 */
	private boolean isValidBSTHelper(PasswordNode currentNode, Password lowerBound, Password upperBound) {
//MUST BE IMPLEMENTED RECURSIVELY
		// BASE CASE 1: the tree rooted at currentNode is empty, which does not violate
		// any BST rules
		if (currentNode == null) {
			return true;
		}

		// BASE CASE 2: the current Password is outside of the upper OR lower bound for
		// this subtree, which is against
		// the rules for a valid BST
		if (currentNode.getPassword().compareTo(lowerBound, COMPARISON_CRITERIA) <= 0
				|| currentNode.getPassword().compareTo(upperBound, COMPARISON_CRITERIA) >= 0) {
			return false;
		}

		// If we do not have a base case situation, we must use recursion to verify
		// currentNode's child subtrees

		// RECURSIVE CASE 1: Check that the left subtree contains only Passwords greater
		// than lowerBound and less than
		// currentNode's Password; return false if this property is NOT satisfied
		boolean leftSubtreeIsValid = isValidBSTHelper(currentNode.getLeft(), lowerBound, currentNode.getPassword());

		// RECURSIVE CASE 2: Check that the right subtree contains only Passwords
		// greater than currentNode's Password
		// and less than upperBound; return false if this property is NOT satisfied
		boolean rightSubtreeIsValid = isValidBSTHelper(currentNode.getRight(), currentNode.getPassword(), upperBound);
		// COMBINE RECURSIVE CASE ANSWERS: this is a valid BST if and only if both case
		// 1 and 2 are valid
		return leftSubtreeIsValid && rightSubtreeIsValid;
	}

	/**
	 * Returns the password that matches the criteria of the provided key. Ex. if
	 * the COMPARISON CRITERIA is OCCURRENCE and the key has an occurrence of 10 it
	 * will return the password stored in the tree with occurrence of 10
	 * 
	 * @param key, the password that contains the information for the password we
	 *             are searching for
	 * @return the Password that matches the search criteria, if it does not exist
	 *         in the tree it this will be null
	 */
	public Password lookup(Password key) {
		return lookupHelper(key, root);
	}

	/**
	 * Recursive helper method to find the matching password in this BST
	 * 
	 * @param key,         password containing the information we are searching for
	 * @param currentNode, the node that is the current root of the tree
	 * @return the Password that matches the search criteria, if it does not exist
	 *         in the tree it this will be null
	 */
	private Password lookupHelper(Password key, PasswordNode currentNode) {
		// Base case: If the current node is null, we have reached the end of the tree,
		// and the password does not exist in the tree. Return null.
		if (currentNode == null) {
			return null;
		}

		// Compare the key password with the password stored in the current node
		int comparisonResult = key.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA);

		// If the passwords match based on the comparison criteria, return the current
		// node's password.
		if (comparisonResult == 0) {
			return currentNode.getPassword();
		}
		// If the key password is "less than" the current node's password, search the
		// left subtree.
		else if (comparisonResult < 0) {
			return lookupHelper(key, currentNode.getLeft());
		}
		// If the key password is "greater than" the current node's password, search the
		// right subtree.
		else {
			return lookupHelper(key, currentNode.getRight());
		}
	}

	/**
	 * Returns the best (max) password in this BST
	 *
	 * @return the best password in this BST
	 * @throws NoSuchElementException if the BST is empty
	 */
	public Password getBestPassword() {
		if (isEmpty()) {
			throw new NoSuchElementException("The tree is empty.");
		}

		PasswordNode currentNode = root;
		while (currentNode.getRight() != null) {
			currentNode = currentNode.getRight();
		}

		return currentNode.getPassword();
	}

	/**
	 * Returns the worst password in this BST
	 *
	 * @return the worst password in this BST
	 * @throws NoSuchElementException if the BST is empty
	 */
	public Password getWorstPassword() {
		if (isEmpty()) {
			throw new NoSuchElementException("The tree is empty.");
		}

		PasswordNode currentNode = root;
		while (currentNode.getLeft() != null) {
			currentNode = currentNode.getLeft();
		}

		return currentNode.getPassword();
	}

	/**
	 * Adds the Password to this BST.
	 * 
	 * @param toAdd the password to be added to the tree
	 */
	public void addPassword(Password toAdd) {
		root = addPasswordHelper(toAdd, root);
	}

	/**
	 * Recursive helper that traverses the tree and adds the password where it
	 * belongs
	 *
	 * @param toAdd,       the password to add to the tree
	 * @param currentNode, the node that is the current root of the (sub)tree
	 * @return the modified PasswordNode after adding the password
	 */
	private PasswordNode addPasswordHelper(Password toAdd, PasswordNode currentNode) {
		// Base case: Found an empty spot to insert the new password
		if (currentNode == null) {
			size++; // Increment the size when a new node is added
			return new PasswordNode(toAdd);
		}

		// Compare the toAdd password with the password in the current node
		int cmp = toAdd.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA);

		if (cmp < 0) {
			// toAdd is "less than" the current password, so insert it in the left subtree
			currentNode.setLeft(addPasswordHelper(toAdd, currentNode.getLeft()));
		} else if (cmp > 0) {
			// toAdd is "greater than" the current password, so insert it in the right
			// subtree
			currentNode.setRight(addPasswordHelper(toAdd, currentNode.getRight()));
		}
		// No duplicates allowed, so we don't need an "else" branch here

		return currentNode;
	}

	/**
	 * Removes the matching password from the tree
	 * 
	 * @param toRemove, the password to be removed from the tree
	 * @throws NoSuchElementException if the password is not in the tree
	 */
	public void removePassword(Password toRemove) {
		root = removePasswordHelper(toRemove, root);
		size--;
	}

	/**
	 * Recursive helper method to remove the password from this BST.
	 *
	 * @param toRemove,    the password to be removed from the tree
	 * @param currentNode, the root of the tree we are removing from
	 * @return the PasswordNode representing the NEW root of this subtree now that
	 *         toRemove has been removed. This may still be currentNode, or it may
	 *         have changed!
	 */
	private PasswordNode removePasswordHelper(Password toRemove, PasswordNode currentNode) {
		// Base case: If the current node is null, we have reached the end of the tree,
		// and the password does not exist in the tree.
		if (currentNode == null) {
			throw new NoSuchElementException("Password not found.");
		}

		// Compare the toRemove password with the password in the current node
		int comparisonResult = toRemove.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA);

		if (comparisonResult < 0) {
			// toRemove is "less than" the current password, so remove it from the left
			// subtree
			currentNode.setLeft(removePasswordHelper(toRemove, currentNode.getLeft()));
		} else if (comparisonResult > 0) {
			// toRemove is "greater than" the current password, so remove it from the right
			// subtree
			currentNode.setRight(removePasswordHelper(toRemove, currentNode.getRight()));
		} else {
			// Found the node to remove
			if (currentNode.isLeafNode()) {
				// Case 1: The current node is a leaf node, simply remove it
				return null;
			} else if (!currentNode.hasLeftChild()) {
				// Case 2: The current node has only a right child, remove the current node and
				// return its right child
				return currentNode.getRight();
			} else if (!currentNode.hasRightChild()) {
				// Case 3: The current node has only a left child, remove the current node and
				// return its left child
				return currentNode.getLeft();
			} else {
				// Case 4: The current node has two children, find the successor password and
				// replace the current node

				// Find the leftmost node in the right subtree (successor)
				PasswordNode successorParent = currentNode;
				PasswordNode successor = currentNode.getRight();
				while (successor.getLeft() != null) {
					successorParent = successor;
					successor = successor.getLeft();
				}

				// Move the successor's right subtree to its parent's left (remove the successor
				// from its original position)
				successorParent.setLeft(successor.getRight());

				// Set the current node's password and right child with the successor's password
				// and right child
				currentNode.setPasswordNode(successor.getPassword(), successor.getRight());
			}
		}

		return currentNode;
	}
}
