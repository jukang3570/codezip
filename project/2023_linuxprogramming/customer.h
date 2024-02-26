
#define MAX 40
#define START_ID 1

struct customer {
	int id;
	char name[MAX];
	int count;
	double cost;
	double wallet;
	struct customer* next;

};
