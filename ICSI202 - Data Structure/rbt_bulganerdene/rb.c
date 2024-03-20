#include "DS.h"


/*
  Хоёр оюутны мэдээллийн хооронд нь харьцуулах функц
*/
int less(const Student *a, const Student *b) { 
  return strcmp(a->id, b->id); 
  }

int isRed(Elm *red) {
  if (red == NULL)
    return 0;
  return red->color;
}

void flipColors(Elm *node) {
  node->color = 1;
  node->R->color = 0;
  node->L->color = 0;
}

Elm *rotateRight(Elm *node) {
  Elm *temp = node->L;
  node->L = temp->R;
  temp->R = node;
  temp->color = node->color;
  node->color = 1;
  return temp;
}

Elm *rotateLeft(Elm *node) {
  Elm *temp = node->R;
  node->R = temp->L;
  temp->L = node;
  temp->color = node->color;
  node->color = 1;
  return temp;
}
Elm *insert(Elm *elm, Student *p) {
  if (elm == NULL) {
    Elm *temp = NULL;
    temp = (Elm *)malloc(sizeof(Elm));
    temp->color = 1;
    temp->R = NULL;
    temp->L = NULL;
    temp->x.age = p->age;
    temp->x.gpa = p->gpa;
    strcpy(temp->x.id, p->id);
    strcpy(temp->x.name, p->name);
    return temp;
  }
  if (less(&elm->x, p) == 0) {
    elm->x.age = p->age;
    elm->x.gpa = p->gpa;
    strcpy(elm->x.id, p->id);
    strcpy(elm->x.name, p->name);
  } else if (less(&elm->x, p) < 0) {
    elm->L = insert(elm->L, p);
  } else {
    elm->R = insert(elm->R, p);
  }

  if (isRed(elm->R) && !isRed(elm->L)) {
    elm = rotateLeft(elm);
  }
  if (isRed(elm->L) && isRed(elm->L->L)) {
    elm = rotateRight(elm);
  }
  if (isRed(elm->L) && isRed(elm->R)) {
    flipColors(elm);
  }
  return elm;
}
/*
  `ptree`-ийн зааж байгаа модонд `x` утгыг оруулна.
  Мод хоосон байсан бол `ptree->root` хаяг өөрчлөгдөж шинээр орсон оройг заана.
  Хэрэв мод тэнцвэрээ алдсан бол тохирох тэнцвэржүүлэх үйлдлүүдийг хийнэ.
 */
void rb_put(RBT *ptree, Student *px) {
  if (ptree->root == NULL) {
    ptree->root = (Elm *)malloc(sizeof(Elm));
    ptree->root->x = *px;
    ptree->root->L = NULL;
    ptree->root->R = NULL;
    ptree->root->color = 0;
  } else {
    ptree->root = insert(ptree->root, px);
  }
}

/*
  `ptree`-ийн зааж байгаа модноос `x` утгыг хайн олдсон оройн `Elm*` хаягийг
  буцаана. Олдохгүй бол `NULL` хаягийг буцаана. Мод дандаа ялгаатай элементүүд
  хадгална гэж үзэж болно.
 */
Elm *rb_get(const RBT *ptree, const char id[]) {
  Elm *temp = ptree->root;
  while (temp != NULL) {
    if (strcmp(temp->x.id, id) == 0) {
      return temp;
    } else if (strcmp(temp->x.id, id) < 0) {
      temp = temp->L;
    } else if (strcmp(temp->x.id, id) > 0) {
      temp = temp->R;
    }
  }
  return NULL;
}

/*
  Устгах функц: ТМноос `x` утгыг хайж олоод устгана.
  Олдохгүй бол юу ч хийхгүй.
  Хэрэв мод тэнцвэрээ алдсан бол тохирох тэнцвэржүүлэх үйлдлүүдийг хийнэ.
 */
Elm *del(Elm *subtree, const char id[]) {
  if (subtree == NULL) {
    return NULL;
  }
  if (strcmp(subtree->x.id, id) == 0) {
    if (subtree->L == NULL && subtree->R == NULL) {
      free(subtree);
      return NULL;
    } else if (subtree->L == NULL) {
      Elm *temp = subtree->R;
      free(subtree);
      return temp;
    } else if (subtree->R == NULL) {
      Elm *temp = subtree->L;
      free(subtree);
      return temp;
    } else {
      Elm *temp = subtree->R;
      while (temp->L != NULL) {
        temp = temp->L;
      }
      subtree->x = temp->x;
      subtree->R = del(subtree->R, temp->x.id);
    }
  } else if (strcmp(subtree->x.id, id) < 0) {
    subtree->L = del(subtree->L, id);
  } else if (strcmp(subtree->x.id, id) > 0) {
    subtree->R = del(subtree->R, id);
  } else if (strcmp(subtree->x.id, id) < 0) {
    subtree->L = del(subtree->L, id);
  } else if (strcmp(subtree->x.id, id) > 0) {
    subtree->R = del(subtree->R, id);
  }
  return subtree;
}
void rb_del(RBT *ptree, const char id[]) {
  ptree->root = del(ptree->root, id);
}

/*
  `ptree`-ийн зааж байгаа модонд `x` утгыг хайж олдсон бол 1, олдохгүй бол 0
  утгыг буцаана.
 */
int rb_find(const RBT *ptree, const char id[]) {
  // Функцийг хэрэгжүүлнэ үү
  if (ptree->root == NULL)
    return 0;
  Elm *temp = ptree->root;
  while (temp != NULL) {
    if (strcmp(temp->x.id, id) == 0) {
      return 1;
    } else if ((strcmp(temp->x.id, id)) < 0) {
      temp = temp->L;
    } else {
      temp = temp->R;
    }
  }
  return 0;
}

void print(Elm *p) {
  if (p)
    printf("%s %s %d %.1f\n", p->x.name, p->x.id, p->x.age, p->x.gpa);
  else
    printf("None\n");
}
