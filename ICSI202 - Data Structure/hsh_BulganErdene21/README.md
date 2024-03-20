# Хайш хүснэгт
![GitHub Classroom Workflow](../../workflows/GitHub%20Classroom%20Workflow/badge.svg?branch=main) ![Points badge](../../blob/badges/.github/badges/points.svg)

Оюутны мэдээлэл хадгалах гинжин хайш хүснэгтийг хэрэгжүүл. 
Давхцлыг динамик жагсаалт ашиглан хэрэгжүүлнэ. Та 7-р даалгавар дээр хэрэгжүүлсэн динамик жагсаалтаа өөрчлөн ашиглана уу. **Таны програм 1 сая ширхэг оюутны мэдээлэл дээр 1 секунтад багтаж ажиллах ёстой**


Оюутны мэдээллийг хадгалахдаа ID-аар нь хайш-лан, хайш хүснэгтэд хадгална. Эхлээд та оюутнуудын нэр, овог, ID-г хадгалсан `student.info` файлаас оюутны мэдээллийг хайш хүснэгтэд хадгална. Дараагаар нь `input.txt` файл доторх үйлдлүүдийг гүйцэтгэнэ. Үйлдлүүд нь дараах төрлийнх байна.

  * **search** id: id-р оюутны мэдээллийг хайж олдсон оюутны овог, нэр, id-г хоосон зайгаар тусгаарлан шинэ мөрөнд хэвлэнэ. Олдохгүй бол "xooson" гэж хэвлэнэ.
  * **update** ovog ner id: id-аар оюутны мэдээллийг хайж олж оюутны овог нэрийг солино. Олдохгүй бол шинээр оруулна.
  * **delete** id: id-р оюутныг хайн олж мэдээллийг устгана. Олдохгүй бол юу ч хийхгүй.

## student.info файл
Эхний мөрөнд нийт оюутны мэдээллийн тоо болох `n` утга байна. Дараагийн `n` ширхэг мөр тус бүр нэг оюутны мэдээлэл агуулах ба овог, нэр, id байна. Овог, нэрийн урт 20-с хэтрэхгүй, id-ийн урт нь 10-с хэтрэхгүй.
Жишээ нь 
```
3
Dellamonica Frances DRS0183434
Buttermore Katlyn CFA8969603
Benzee Evalyn DHM6868470
```

## input.txt файл

Эхний мөрөнд нийт оюутны мэдээллийн тоо болох `m` утга байна. Дараагийн `m` мөр тус бүр нэг үйлдлийг заана. Үйлдэл нь
 * **search** id
 * **update** ovog ner id
 * **delete** id
гэсэн 3 янз байна. 

Жишээ нь 
```
8
search OVE8511071
search DHM6868470
delete XFN9850033
update Alkire Cecelia DHM6868470
search CFA8969603
search DHM6868470
delete DRS0183434
search DRS0183434
```
8 үйлдэл хийх бөгөөд мөрийн эхэнд хийгдэх үйлдэл дараагаар нь үйлдлийн параметр утгууд байна.

## output.txt гаралт

**search** үйлдлээр олдсон оюутны мэдээллийг `student_print` функцээр хэвлэнэ. `search` үйлдлийн хийгдсэн дарааллаар, тус бүрийн гаралтыг нэг мөрөнд бичнэ. 

Жишээ нь дээрх 8 үйлдэлд тохирох гаралт нь
```
xooson
Benzee Evalyn DHM6868470
Buttermore Katlyn CFA8969603
Alkire Cecelia DHM6868470
xooson
```
байна.

## Хэрэгжүүлэх заавар

Уг даалгавраар оюутны мэдээллийг хадгалах ба оюутанд дараах мэдээллүүдтэй.

| Оюутан |
|:------:|
| Овог   |
| Нэр    |
| ID     |

Уг мэдээллийг дараах `Student` бүтцэд хадгална.
```C
struct Student {
        char ovog[20];
        char ner[20];
        char id[11];
};
typedef struct Student Student;
```

Даалгавраар гинжин хайш хүснэгт үүсгэх учраас давхцлыг жагсаалт ашиглан хийнэ. Тийм болохоор 7-р даалгавраар хэрэгжүүлсэн динамик `List`-ыг `int` биш `Student` төрөл хадгалдаг болгон өөрчилнө. Тиймээс жагсаалтад хадгалагдах `Elm` бүтэц маань дараах хэлбэртэй болно.
```C
struct Elm {
        Student x;
        struct Elm *next;
};
typedef struct Elm Elm;

```

Мөн `list.c` доторх функцүүдийг `Student` хадгалахаар өөрчилнө.
```C
void l_push_back(List *p, Student x);
void l_push_front(List *p, Student x);
void l_insert(List *p, Student x, int pos);
void l_pop_front(List *p);
void l_pop_back(List *p);
void l_erase(List *p, int pos);
void l_print(List *p);
Elm *l_search(List *p, char id[]);
```


## Аль хэдийн хэрэгжүүлсэн функцүүд

 * Оюутны мэдээлэл оруулах функц: `p_hash`-ын зааж байгаа хайш хүснэгтэд `x`-д хадгалсан оюутны мэдээллийг оруулна.
   ```C
   void ht_insert(HashTable *p_hash, const Student x);
   {
        int h = ht_hash(x.id);
        Elm *p_elm = l_search(&p_hash->h[h], x.id);
        if (p_elm == NULL)
                l_push_back(&p_hash->h[h], x);
   }
   ```
   Оруулахдаа `ht_hash` функцээр ID-ыг хайшд шилжүүлэн, тэр хайш утгаар нь жагсаалтан хүснэгтийн индекс болгон оруулах жагсаалтыг олж авна. Олсон жагсаалтдаа `l_push_back` функцийг дуудан оюутны мэдээллийг оруулна.
   
 * Оюутны мэдээллийг хэвлэх функц: `p`-ын зааж буй оюутны овог, нэр, ID-г `fout` файлд хэвлэнэ.
   ```C
   void student_print(FILE *fout, const Student *p)
   {
        if (p == NULL)
                fprintf(fout, "xooson\n");
        else
                fprintf(fout, "%s %s %s\n", p->ovog, p->ner, p->id);
   }
   ```
 * Хайш хүснэгтийн утгуудыг эхлүүлэх функц: Хайш хүснэгт бол жагсаалтан хүснэгт юм. Тиймээс хүснэгтийн бүх элементийн `head`, `tail` утгуудад `NULL` оноох ёстой.
   ```C
   void ht_init(HashTable *p_hash)
   {
        int i;
        p_hash->h = (List *) malloc(sizeof(List) * HASH_TABLE_SIZE);
        for (i = 0; i < HASH_TABLE_SIZE; i++)
                p_hash->h[i].head = p_hash->h[i].tail = NULL;
   }
   ```
 * Хайш хүснэгтийг чөлөөлөх: Ойн цоорхой үүсгэлгүй, бүх элементийг устган, хаягийг чөлөөлнө.
   ```C
   void ht_release(HashTable *p_hash)
   {
        int i;
        for (i = 0; i < HASH_TABLE_SIZE; i++) {
                while (p_hash->h[i].head != NULL)
                        l_pop_front(&p_hash->h[i]);
        }
        free(p_hash->h);
   }
   ```
 
## Хэрэгжүүлэх функцүүд

 * Хайш тооцоолох функц: `s` тэмдэгтэн цувааны хайшыг тооцоолоод, тооцоолсон хайшыг буцаах функц.
   ```C
   int ht_hash(const char s[]);
   ```
 * Хайх функц: `p_hash`-ыг зааж буй хайш хүснэгтээс `id`-тай оюутныг хайж, олдсон оюутны хаягийг буцаах функц. Олдохгүй бол `NULL` хаяг буцаана.
   ```C
   Student *ht_search(HashTable *p_hash, const char id[]);
   ```
 * Мэдээлэл өөрчлөх функц: `p_hash`-ыг зааж буй хайш хүснэгтээс `x.id`-аар оюутныг хайж олоод, овог нэрийг `x.ner`, `x.ovog`-оор солино. Олдохгүй бол `x` оюутныг шинээр оруулна.
   ```C
   void ht_update(HashTable *p_hash, const Student x);
   ```
 * Устгах функц: `p_hash`-ыг зааж буй хайш хүснэгтээс `id`-тай оюутныг хайж олж устгана. Олдохгүй бол юу ч хийхгүй.
   ```C
   void ht_del(HashTable *p_hash, const char id[]);
   ```
