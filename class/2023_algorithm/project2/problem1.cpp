#include <iostream>

struct TreeNode {
    int value;
    TreeNode* left;
    TreeNode* right;

    TreeNode(int val) : value(val), left(nullptr), right(nullptr) {}
};

TreeNode* insert(TreeNode* root, int value) {
    if (root == nullptr) {
        return new TreeNode(value);
    }
    if (value < root->value) {
        root->left = insert(root->left, value);
    } else {
        root->right = insert(root->right, value);
    }
    return root;
}

TreeNode* findMinValueNode(TreeNode* node) {
    TreeNode* current = node;
    while (current->left != nullptr) {
        current = current->left;
    }
    return current;
}

TreeNode* deleteNode(TreeNode* root, int value) {
    if (root == nullptr) {
        return root;
    }
    if (value < root->value) {
        root->left = deleteNode(root->left, value);
    } else if (value > root->value) {
        root->right = deleteNode(root->right, value);
    } else {
        if (root->left == nullptr) {
            TreeNode* temp = root->right;
            delete root;
            return temp;
        } else if (root->right == nullptr) {
            TreeNode* temp = root->left;
            delete root;
            return temp;
        }
        TreeNode* temp = findMinValueNode(root->right);
        root->value = temp->value;
        root->right = deleteNode(root->right, temp->value);
    }
    return root;
}

void preorderTraversal(TreeNode* root) {
    if (root == nullptr) {
        return;
    }
    std::cout << root->value << " ";
    preorderTraversal(root->left);
    preorderTraversal(root->right);
}

void inorderTraversal(TreeNode* root) {
    if (root == nullptr) {
        return;
    }
    inorderTraversal(root->left);
    std::cout << root->value << " ";
    inorderTraversal(root->right);
}

void postorderTraversal(TreeNode* root) {
    if (root == nullptr) {
        return;
    }
    postorderTraversal(root->left);
    postorderTraversal(root->right);
    std::cout << root->value << " ";
}

int main() {
    int n;
    std::cin >> n;
    TreeNode* root = nullptr;

    for (int i = 0; i < n; i++) {
        int element;
        std::cin >> element;
        root = insert(root, element);
    }

    int k;
    std::cin >> k;

    for (int i = 0; i < k; i++) {
        int p, x;
        std::cin >> p >> x;
        root = deleteNode(root, x);

        if (p == 0) {
            preorderTraversal(root);
        } else if (p == 1) {
            inorderTraversal(root);
        } else if (p == 2) {
            postorderTraversal(root);
        }

        std::cout << std::endl;
    }

    return 0;
}
