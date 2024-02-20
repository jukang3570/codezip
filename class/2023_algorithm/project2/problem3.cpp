#include <iostream>
#include <vector>

int hashFunction(int x, int i, int M) {
    return (x + i) % M;
}

bool isPrime(int num) {
    if (num <= 1) return false;
    if (num <= 3) return true;
    if (num % 2 == 0 || num % 3 == 0) return false;
    for (int i = 5; i * i <= num; i += 6) {
        if (num % i == 0 || num % (i + 2) == 0) return false;
    }
    return true;
}

int findSmallestPrime(int num) {
    while (true) {
        if (isPrime(num)) return num;
        num++;
    }
}

int main() {
    int N;
    std::cin >> N;
    std::vector<int> numbers(N);

    for (int i = 0; i < N; i++) {
        std::cin >> numbers[i];
    }

    int M = findSmallestPrime(2 * N);
    int collisionCount = 0;

    std::vector<int> hashTable(M, -1);

    for (int i = 0; i < N; i++) {
        int x = numbers[i];
        int index = hashFunction(x, 0, M);
        int j = 1;

        while (hashTable[index] != -1) {
            collisionCount++;
            index = hashFunction(x, j, M);
            j++;
        }

        hashTable[index] = x;
    }

    std::cout << collisionCount << std::endl;

    return 0;
}
