import java.util.NoSuchElementException;

public class PasswordCrackingTester {
	/**
	 * Validates the constructor and accessor methods of PasswordStorage,
	 * specifically the getComparisonCriteria(), size(), and isEmpty() methods, as
	 * well as accessing the protected data field root.
	 *
	 * Be sure to try making multiple PasswordStorage objects with different
	 * Attributes.
	 * 
	 * @return true if the basic accessor methods work as expected, false otherwise
	 */
	public static boolean testBasicPasswordStorageMethods() {
		try {
			// Test empty BST
			PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);
			if (bst.getComparisonCriteria() != Attribute.OCCURRENCE)
				return false;
			if (!bst.isEmpty())
				return false;
			if (bst.size() != 0)
				return false;

			// Test non-empty BST
			Password p1 = new Password("password1", 500);
			Password p2 = new Password("password2", 1000);
			Password p3 = new Password("password3", 200);

			bst.addPassword(p1);
			bst.addPassword(p2);
			bst.addPassword(p3);

			if (bst.getComparisonCriteria() != Attribute.OCCURRENCE)
				return false;
			if (bst.isEmpty())
				return false;
			if (bst.size() != 3)
				return false;

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Validates the Password class compareTo() method. Create at least two
	 * DIFFERENT Password objects and compare them on each of the Attribute values.
	 * See the writeup for details on how the various comparisons are expected to
	 * work.
	 *
	 * @return true if Password's compareTo() works as expected, false otherwise
	 */
	public static boolean testPasswordCompareTo() {
		try {
			Password p1 = new Password("password", 1000);
			Password p2 = new Password("StronkPass12#", 23);
			Password p3 = new Password("Password", 1000);

			// OCCURRENCE: p1 > p2 since 1000 > 23
			if (p1.compareTo(p2, Attribute.OCCURRENCE) <= 0)
				return false;

			// OCCURRENCE: p1 = p3 since 1000 = 1000
			if (p1.compareTo(p3, Attribute.OCCURRENCE) != 0)
				return false;

			// STRENGTH RATING: p1 < p2 since 9.75 < 20.0
			if (p1.compareTo(p2, Attribute.STRENGTH_RATING) >= 0)
				return false;

			// HASHED PASSWORD: p1 < p2 since "5" < "a"
			if (p1.compareTo(p2, Attribute.HASHED_PASSWORD) >= 0)
				return false;

			// HASHED PASSWORD: p1 < p3 since "5" < "8"
			if (p1.compareTo(p3, Attribute.HASHED_PASSWORD) >= 0)
				return false;

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Validates the incomplete methods in PasswordNode, specifically isLeafNode(),
	 * numberOfChildren(), hasLeftChild() and hasRightChild(). Be sure to test all
	 * possible configurations of a node in a binary tree!
	 *
	 * @return true if the status methods of PasswordNode work as expected, false
	 *         otherwise
	 */
	public static boolean testNodeStatusMethods() {
		// Create some sample passwords
		Password p1 = new Password("password123", 10);
		Password p2 = new Password("securePass", 8);
		Password p3 = new Password("admin123", 5);

		// Create nodes with different configurations
		PasswordNode leafNode = new PasswordNode(p1);
		PasswordNode leftChildNode = new PasswordNode(p2);
		PasswordNode rightChildNode = new PasswordNode(p3);
		PasswordNode twoChildrenNode = new PasswordNode(p1, leftChildNode, rightChildNode);
		PasswordNode twoChildrenNode1 = new PasswordNode(p1, leftChildNode, null);

		// Create a password with only one node
		Password singleNodePassword = new Password("singlePass", 6);
		PasswordNode singleNode = new PasswordNode(singleNodePassword);

		// Test isLeafNode()
		boolean isLeafNodePassed = leafNode.isLeafNode(); // should be true
		boolean leftChildNodePassed = leftChildNode.isLeafNode(); // should be true
		boolean rightChildNodePassed = rightChildNode.isLeafNode(); // should be true
		boolean twoChildrenNodePassed = twoChildrenNode.isLeafNode(); // should be false
		boolean singleNodePassed = singleNode.isLeafNode(); // should be true
		boolean twoChildrenNode1Passed = twoChildrenNode1.isLeafNode(); // should be false

		// Test hasLeftChild()
		boolean hasLeftChildPassed1 = leafNode.hasLeftChild(); // should be false
		boolean hasLeftChildPassed2 = leftChildNode.hasLeftChild(); // should be false
		boolean hasLeftChildPassed3 = rightChildNode.hasLeftChild(); // should be false
		boolean hasLeftChildPassed4 = twoChildrenNode.hasLeftChild(); // should be true
		boolean hasLeftChildPassed5 = singleNode.hasLeftChild(); // should be false
		boolean hasLeftChildPassed6 = twoChildrenNode1.hasLeftChild(); // should be true

		// Test hasRightChild()
		boolean hasRightChildPassed1 = leafNode.hasRightChild(); // should be false
		boolean hasRightChildPassed2 = leftChildNode.hasRightChild(); // should be false
		boolean hasRightChildPassed3 = rightChildNode.hasRightChild(); // should be false
		boolean hasRightChildPassed4 = twoChildrenNode.hasRightChild(); // should be true
		boolean hasRightChildPassed5 = singleNode.hasRightChild(); // should be false
		boolean hasRightChildPassed6 = twoChildrenNode1.hasRightChild(); // should be false

		// Test numberOfChildren()
		int numberOfChildrenPassed1 = leafNode.numberOfChildren(); // should be 0
		int numberOfChildrenPassed2 = leftChildNode.numberOfChildren(); // should be 0
		int numberOfChildrenPassed3 = rightChildNode.numberOfChildren(); // should be 0
		int numberOfChildrenPassed4 = twoChildrenNode.numberOfChildren(); // should be 2
		int numberOfChildrenPassed5 = singleNode.numberOfChildren(); // should be 0
		int numberOfChildrenPassed6 = twoChildrenNode1.numberOfChildren(); // should be 1

		// Check if all tests passed
		boolean allTestsPassed = isLeafNodePassed && leftChildNodePassed && rightChildNodePassed
				&& !twoChildrenNodePassed && singleNodePassed && !twoChildrenNode1Passed && !hasLeftChildPassed1
				&& !hasLeftChildPassed2 && !hasLeftChildPassed3 && hasLeftChildPassed4 && !hasLeftChildPassed5
				&& hasLeftChildPassed6 && !hasRightChildPassed1 && !hasRightChildPassed2 && !hasRightChildPassed3
				&& hasRightChildPassed4 && !hasRightChildPassed5 && !hasRightChildPassed6
				&& numberOfChildrenPassed1 == 0 && numberOfChildrenPassed2 == 0 && numberOfChildrenPassed3 == 0
				&& numberOfChildrenPassed4 == 2 && numberOfChildrenPassed5 == 0 && numberOfChildrenPassed6 == 1;

		return allTestsPassed;
	}

// GIVE TO STUDENTS
	public static boolean testToString() {
		try {
			PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);
// empty is empty string
			String expected = "";
			String actual = bst.toString();
			if (!actual.equals(expected)) {
				System.out.println("toString() does not return the proper value on an empty tree!");
				return false;
			}
// size one only returns 1 thing
			Password p = new Password("1234567890", 15000);
			PasswordNode rootNode = new PasswordNode(p);
			bst.root = rootNode; // here I am manually building the tree by editing the root node
// directly to be the node of my choosing
			expected = p.toString() + "\n";
			actual = bst.toString();
			if (!actual.equals(expected))
				return false;
// big tree returns in-order traversal
			Password p2 = new Password("test", 500);
			Password p3 = new Password("iloveyou", 765);
			Password p4 = new Password("qwerty", 250);
			Password p5 = new Password("admin", 1002);
			Password p6 = new Password("password", 2232);
			Password p7 = new Password("abc123", 2090);
			PasswordNode p4Node = new PasswordNode(p4);
			PasswordNode p3Node = new PasswordNode(p3);
			PasswordNode p7Node = new PasswordNode(p7);
			PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
			PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
			PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
			rootNode = new PasswordNode(p, p2Node, p5Node);
			bst.root = rootNode;
			expected = p4.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n" + p.toString() + "\n"
					+ p5.toString() + "\n" + p7.toString() + "\n" + p6.toString() + "\n";
			actual = bst.toString();
			if (!actual.equals(expected))
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Validates the isValidBST method in the PasswordStorage class by testing its
	 * functionality to check if the binary search tree is valid.
	 *
	 * @return true if the test passes successfully, false otherwise
	 */
	public static boolean testIsValidBST() {
		try {
			// Create a PasswordStorage object with the desired comparison criteria
			PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

			// Test an empty tree
			if (!bst.isValidBST()) {
				System.out.println("isValidBST() says that an empty tree is not a valid BST!");
				return false;
			}

			// Test a tree of size 1
			Password p = new Password("1234567890", 1000);
			PasswordNode rootNode = new PasswordNode(p);
			bst.root = rootNode;
			if (!bst.isValidBST()) {
				System.out.println("isValidBST() says that a tree of size 1 is not a valid BST!");
				return false;
			}

			// Test a small obviously invalid tree
			Password p2 = new Password("test", 500);
			Password p3 = new Password("iloveyou", 765);
			Password p4 = new Password("qwerty", 250);
			Password p5 = new Password("admin", 1002);
			Password p6 = new Password("password", 2232);
			Password p7 = new Password("abc123", 2090);

			PasswordNode p7Node = new PasswordNode(p7);
			PasswordNode p3Node = new PasswordNode(p3);
			rootNode = new PasswordNode(p, p7Node, p3Node);
			bst.root = rootNode;
			if (bst.isValidBST())
				return false;

			// Test a tree with only one subtree being valid, other subtree has a violation
			// a couple layers deep
			PasswordNode p4Node = new PasswordNode(p4);
			p7Node = new PasswordNode(p7);
			p3Node = new PasswordNode(p3);
			PasswordNode p6Node = new PasswordNode(p6, null, p7Node);
			PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
			PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
			rootNode = new PasswordNode(p, p2Node, p5Node);
			bst.root = rootNode;
			if (bst.isValidBST()) {
				System.out.println("isValidBST() says that a tree with only one valid subtree is a valid BST");
				return false;
			}

			// Test a valid large tree
			p4Node = new PasswordNode(p4);
			p3Node = new PasswordNode(p3);
			p7Node = new PasswordNode(p7);
			p6Node = new PasswordNode(p6, p7Node, null);
			p5Node = new PasswordNode(p5, null, p6Node);
			p2Node = new PasswordNode(p2, p4Node, p3Node);
			rootNode = new PasswordNode(p, p2Node, p5Node);
			bst.root = rootNode;
			if (!bst.isValidBST())
				return false;

			// Test a tree that violates the search order property further down the tree and
			// comes from a node in a left subtree
			PasswordNode one = new PasswordNode(p4);
			PasswordNode three = new PasswordNode(p3, one, null);
			PasswordNode two = new PasswordNode(p2, null, three);
			bst.root = two;
			if (bst.isValidBST()) {
				System.out.println("bad bst is valid");
				return false;
			}

			// Test a tree that violates the search order property further down the tree and
			// comes from a node in a right subtree
			one = new PasswordNode(p4);
			three = new PasswordNode(p3, null, one);
			two = new PasswordNode(p2, null, three);
			bst.root = two;
			if (bst.isValidBST()) {
				System.out.println("bad bst is valid");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Validates the lookup method in the PasswordStorage class by testing its
	 * functionality to find a password that matches the search criteria.
	 *
	 * @return true if the test passes successfully, false otherwise
	 */
	public static boolean testLookup() {
		// Create a PasswordStorage object with the desired comparison criteria
		PasswordStorage passwordStorage = new PasswordStorage(Attribute.STRENGTH_RATING);

		// Create some sample passwords with valid occurrences values (within the range)
		Password p1 = new Password("password123", 10);
		Password p2 = new Password("securePass", 8);
		Password p3 = new Password("admin123", 5);
		Password p4 = new Password("strongPass$", 15);

		// Add passwords to the PasswordStorage
		passwordStorage.addPassword(p1);
		passwordStorage.addPassword(p2);
		passwordStorage.addPassword(p3);
		passwordStorage.addPassword(p4);

		boolean lookupPassed1 = false, lookupPassed2 = false, lookupPassed3 = false, lookupPassed4 = false;

		try {
			Password lookupPassword1 = passwordStorage.lookup(new Password("admin123", 5)); // valid occurrence
			if (lookupPassword1 != null) {
				lookupPassed1 = lookupPassword1.equals(p3);
			}
		} catch (NullPointerException e) {
			lookupPassed1 = false;
		}

		try {
			Password lookupPassword2 = passwordStorage.lookup(new Password("password123", 10)); // valid occurrence
			if (lookupPassword2 != null) {
				lookupPassed2 = lookupPassword2.equals(p1);
			}
		} catch (NullPointerException e) {
			lookupPassed2 = false;
		}

		try {
			Password lookupPassword3 = passwordStorage.lookup(new Password("securePass", 8)); // valid occurrence
			if (lookupPassword3 != null) {
				lookupPassed3 = lookupPassword3.equals(p2);
			}
		} catch (NullPointerException e) {
			lookupPassed3 = false;
		}

		try {
			Password lookupPassword4 = passwordStorage.lookup(new Password("strongPass$", 15)); // valid occurrence
			if (lookupPassword4 != null) {
				lookupPassed4 = lookupPassword4.equals(p4);
			}
		} catch (NullPointerException e) {
			lookupPassed4 = false;
		}

		// Check if all tests passed
		boolean allTestsPassed = lookupPassed1 && lookupPassed2 && lookupPassed3 && lookupPassed4;

		return allTestsPassed;
	}

	/**
	 * Validates the addPassword method in the PasswordStorage class by testing its
	 * functionality to add passwords to the binary search tree.
	 *
	 * @return true if the test passes successfully, false otherwise
	 */
	public static boolean testAddPassword() {
		// Create a PasswordStorage object with the desired comparison criteria
		PasswordStorage passwordStorage = new PasswordStorage(Attribute.OCCURRENCE);

		// Create some sample passwords with valid occurrences values (within the range)
		Password p1 = new Password("password123", 10);
		Password p2 = new Password("securePass", 8);
		Password p3 = new Password("admin123", 5);
		Password p4 = new Password("strongPass", 15);

		// Add passwords to the PasswordStorage
		passwordStorage.addPassword(p1);
		passwordStorage.addPassword(p2);
		passwordStorage.addPassword(p3);
		passwordStorage.addPassword(p4);

		// Check if the size of the PasswordStorage is as expected
		boolean sizeTestPassed = passwordStorage.size() == 4;

		// Check if the PasswordStorage is a valid BST after adding passwords
		boolean isValidBST = passwordStorage.isValidBST();

		boolean lookupPassed1 = false, lookupPassed2 = false, lookupPassed3 = false, lookupPassed4 = false;

		try {
			Password lookupPassword1 = passwordStorage.lookup(new Password("password123", 10)); // valid occurrence
			if (lookupPassword1 != null) {
				lookupPassed1 = lookupPassword1.equals(p1);
			}
		} catch (NullPointerException e) {
			lookupPassed1 = false;
		}

		try {
			Password lookupPassword2 = passwordStorage.lookup(new Password("securePass", 8)); // valid occurrence
			if (lookupPassword2 != null) {
				lookupPassed2 = lookupPassword2.equals(p2);
			}
		} catch (NullPointerException e) {
			lookupPassed2 = false;
		}

		try {
			Password lookupPassword3 = passwordStorage.lookup(new Password("admin123", 5)); // valid occurrence
			if (lookupPassword3 != null) {
				lookupPassed3 = lookupPassword3.equals(p3);
			}
		} catch (NullPointerException e) {
			lookupPassed3 = false;
		}

		try {
			Password lookupPassword4 = passwordStorage.lookup(new Password("strongPass", 15)); // valid occurrence
			if (lookupPassword4 != null) {
				lookupPassed4 = lookupPassword4.equals(p4);
			}
		} catch (NullPointerException e) {
			lookupPassed4 = false;
		}

		// Check if all tests passed
		boolean allTestsPassed = sizeTestPassed && isValidBST && lookupPassed1 && lookupPassed2 && lookupPassed3
				&& lookupPassed4;

		return allTestsPassed;
	}

	/**
	 * Validates the removePassword method in the PasswordStorage class by testing
	 * its functionality to remove passwords from the binary search tree.
	 *
	 * @return true if the test passes successfully, false otherwise
	 */
	public static boolean testRemovePassword() {
		// Create a PasswordStorage object with the desired comparison criteria
		PasswordStorage passwordStorage = new PasswordStorage(Attribute.OCCURRENCE);

		// Create some sample passwords
		Password p1 = new Password("password123", 10);
		Password p2 = new Password("securePass", 8);
		Password p3 = new Password("admin123", 5);
		Password p4 = new Password("testPass$123", 4);
		Password p5 = new Password("pass", 2);

		// Add passwords to the PasswordStorage
		passwordStorage.addPassword(p1);
		passwordStorage.addPassword(p2);
		passwordStorage.addPassword(p3);
		passwordStorage.addPassword(p4);
		passwordStorage.addPassword(p5);

		// Check if the size of the PasswordStorage is as expected
		boolean sizeTestPassed = passwordStorage.size() == 5;

		// Check if the PasswordStorage is a valid BST after adding passwords
		boolean isValidBST = passwordStorage.isValidBST();

		// Save the string representation of the tree before removal
		String treeBeforeRemoval = passwordStorage.toString();

		// Remove passwords from the PasswordStorage with valid occurrences
		boolean removed1 = false, removed2 = false;
		try {
			passwordStorage.removePassword(new Password("password123", 10));
			removed1 = true;
		} catch (NoSuchElementException e) {
			removed1 = false;
		}

		try {
			passwordStorage.removePassword(new Password("admin123", 5));
			removed2 = true;
		} catch (NoSuchElementException e) {
			removed2 = false;
		}

		// Check tree after removal
		String treeAfterRemoval = passwordStorage.toString();

		// Check if the tree structure has changed after removal
		boolean treeStructureChanged = !treeBeforeRemoval.equals(treeAfterRemoval);

		// Check if the PasswordStorage is still a valid BST after removal
		boolean isValidBSTAfterRemoval = passwordStorage.isValidBST();

		// Check if the passwords that should be removed still exist
		boolean removedPasswordsExist = passwordStorage.lookup(p1) != null || passwordStorage.lookup(p3) != null;

		// Check if the subtree of removed passwords still exists
		boolean subtreeStillExists = passwordStorage.lookup(p2) != null || passwordStorage.lookup(p4) != null
				|| passwordStorage.lookup(p5) != null;

		// Check if the size of the PasswordStorage is as expected after removal
		boolean sizeAfterRemovalTestPassed = passwordStorage.size() == 3;

		// Lookup the passwords to verify they were removed correctly
		boolean lookupPassed1 = passwordStorage.lookup(p1) == null;
		boolean lookupPassed2 = passwordStorage.lookup(p2) != null && passwordStorage.lookup(p2).equals(p2);
		boolean lookupPassed3 = passwordStorage.lookup(p3) == null;
		boolean lookupPassed4 = passwordStorage.lookup(p4) != null && passwordStorage.lookup(p4).equals(p4);
		boolean lookupPassed5 = passwordStorage.lookup(p5) != null && passwordStorage.lookup(p5).equals(p5);

		// Check if all tests passed
		boolean allTestsPassed = removed1 && removed2 && !removedPasswordsExist && subtreeStillExists && sizeTestPassed
				&& isValidBST && sizeAfterRemovalTestPassed && isValidBSTAfterRemoval && lookupPassed1 && lookupPassed2
				&& lookupPassed3 && lookupPassed4 && lookupPassed5 && treeStructureChanged;

		return allTestsPassed;
	}

	public static void main(String[] args) {
		runAllTests();
	}

	public static boolean runAllTests() {
		boolean compareToPassed = testPasswordCompareTo();
		boolean nodeStatusPassed = testNodeStatusMethods();
		boolean basicMethodsPassed = testBasicPasswordStorageMethods();
		boolean toStringPassed = testToString();
		boolean isValidBSTPassed = testIsValidBST();
		boolean lookupPassed = testLookup();
		boolean addPasswordPassed = testAddPassword();
		boolean removePasswordPassed = testRemovePassword();
		System.out.println("Password compareTo: " + (compareToPassed ? "PASS" : "FAIL"));
		System.out.println("PasswordNode Status Methods: " + (nodeStatusPassed ? "PASS" : "FAIL"));
		System.out.println("PasswordStorage Basic Methods: " + (basicMethodsPassed ? "PASS" : "FAIL"));
		System.out.println("PasswordStorage toString: " + (toStringPassed ? "PASS" : "FAIL"));
		System.out.println("PasswordStorage isValidBST: " + (isValidBSTPassed ? "PASS" : "FAIL"));
		System.out.println("PasswordStorage lookup: " + (lookupPassed ? "PASS" : "FAIL"));
		System.out.println("PasswordStorage addPassword: " + (addPasswordPassed ? "PASS" : "FAIL"));
		System.out.println("PasswordStorage removePassword: " + (removePasswordPassed ? "PASS" : "FAIL"));
// AND ANY OTHER ADDITIONAL TEST METHODS YOU MAY WANT TO WRITE!
		return compareToPassed && nodeStatusPassed && basicMethodsPassed && toStringPassed && isValidBSTPassed
				&& lookupPassed && addPasswordPassed && removePasswordPassed;
	}
}
