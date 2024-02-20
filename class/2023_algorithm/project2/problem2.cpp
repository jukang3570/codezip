#include <iostream>

struct TreeNode {
    int key;
    TreeNode* left;
    TreeNode* right;

    TreeNode(int k) : key(k), left(nullptr), right(nullptr) {}
};

int treeInsert(TreeNode*& root, int x, int& callCount) {
    if (root == nullptr) {
        root = new TreeNode(x);
        callCount++;
        return x;
    }
    if (x < root->key) {
        callCount++;
        treeInsert(root->left, x, callCount);
    } else {
        callCount++;
        treeInsert(root->right, x, callCount);
    }
    return x;
}

int main() {
    int n;
    std::cin >> n;
    int callCount = 0;
    TreeNode* root = nullptr;

    for (int i = 0; i < n; i++) {
        int element;
        std::cin >> element;
        treeInsert(root, element, callCount);
    }

    std::cout << callCount << std::endl;

    return 0;
}
