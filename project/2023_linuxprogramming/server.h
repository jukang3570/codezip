#define MAX 40
#define START_ID 1

struct server {
   int id;
   char name[MAX];
   double cost;
   int count;
   struct server* next;

};