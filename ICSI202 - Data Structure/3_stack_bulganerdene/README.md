# Стак, Дараалал, Жагсаалтыг хүснэгтээр хэрэгжүүлэх
![GitHub Classroom Workflow](../../workflows/GitHub%20Classroom%20Workflow/badge.svg?branch=main) ![Points badge](../../blob/badges/.github/badges/points.svg)

Энэ хүү даалгавраар стак (stack), дараалал (queue), жагсаалт (list) бүтцүүдийг хүснэгт ашиглан хэрэгжүүлэх болно.

## Ерөнхий файл
`DS.h` нь бүтцийн ерөнхий толгой файл. Дотор нь Stack, Queue, List бүтцүүд тодорхойлогдсон ба тэдгээр дээр хийгдэх үйлдлүүдийн функцийн зарлалт байна. Уг файлыг өөрчлөх ёсгүй.

## Makefile
`Makefile` нь `make` хэрэгсэл ашиглан компайл хийх команд бичигдсэн байна. Уг файлыг томоохон төсөлд ашигладаг бөгөөд компайл хийх үйлдлүүдийг салгаснаар зөвхөн өөрчлөлт орсон файлыг салгаж компайл хийх боломж бүрддэг.

Компайл хийх процесс нь дараах шатуудаас бүрддэг.
  1. Урьдчилан боловсруулах (Preprocess): Макро болон preprocess командуудыг задлан нэг файлд хийнэ. Жишээ нь `#include`-ээр агуулсан бүх файлын доторхыг тухайн файлд нэмж залган кодын файлыг баяжуулна.  
  2. Компайл хийх (Compile): Баяжуулсан файлыг компайл хийж ассемблер код болно.  
  3. Ассембле хийх (Assemble): Ассеблер кодыг тухайн системийн архитектурт нийцсэн хоёртын файл болгон гаргана. Уг файлыг объект код гэж нэрлэдэг.  
  4. Холбох (Link): Объект кодуудыг нэгтгэн ажиллуулж болох файл буюу exe файлыг гарга. Уг файл, бидний ажиллуулдаг програм болно.  

Дараах мөр `stack.o` гэдэг объект файл нь `stack.c, DS.h` файлуудаас хамаарахыг илтгэнэ. Ингэснээр `stack.c, DS.h` файлуудад өөрчлөлт орсон бол заагдсан командын дагуу `stack.o`-г шинээр үүсгэнэ.
```
stack.o: stack.c DS.h
	gcc -c stack.c -o stack.o
```

`make` ашиглаж компайл хийхдээ тухайн хавтас дотроос дараах командаар компайл хийнэ (make суусан бөгөөд програм хайх зам дээр байх ёстой). 
```
make
```
Үр дүнд нь `all: lqs` мөр ажиллаж `lqs` програм файлыг үүсгэнэ. Үүнийг
```shell
./lqs
```
командаар ажиллуулна.  

Програмыг компайл хийхэд заавал `make` ашиглах шаардлагагүй. Дараах командаар компайл хийх боломжтой.
```shell
gcc *.c -o lqs
./lqs
```


## Даалгаврыг хийх заавар

Энэ даалгаврыг хийхэд шаардлагатай мэдлэг:
  1. Си хэлний бүтэц (struct)
  2. Си хэлний хаяган хувьсагч, дам хандалтын ойлголт (pointer, dereference)

### Стак (Stack)

Стак нь хамгийн сүүлд оруулаад, сүүлээс гаргадаг өгөгдлийн бүтэц юм. Дараах үйлдлүүдийг хийх функцүүдийг хэрэгжүүлнэ. Дараах функцүүд нь `Stack` бүтцийн гишүүдэд хандах буюу `p->s_arr[]` хүснэгт болон түүний элементүүдийн тоог хадгалах `p->s_len` хувьсагчидтай харьцана.
  * Төгсгөлд нь оруулах үйлдэл. p-ийн зааж буй Stack-т x утгыг оруулна
    ```C
    void s_push(Stack *p, int x)
    ```
  * Төгсгөлөөс нь гаргах үйлдэл. p-ийн зааж буй Stack-аас гарах функц
    ```C
    void s_pop(Stack *p)
    ```

### Дараалал (Queue)

Дараалал гэдэг нь хамгийн төгсгөлд нь оруулаад, гаргахдаа хамгийн эхнээс нь гаргах функц.
Дараах функцүүд нь `Queue` бүтцийн гишүүдэд хандах буюу `p->q_arr[]` хүснэгт болон түүний элементүүдийн тоог хадгалах `p->q_len` хувьсагчидтай харьцана.
  * Төгсгөлд нь оруулах үйлдэл. p-ийн зааж буй Queue-д x утгыг хийнэ.
    ```C
    void q_push(Queue *p, int x)
    ```
  * Эхлэлээс нь гаргах үйлдэл. p-ийн зааж буй Queue-с гаргана. `p->q_arr` хүснэгтийн элементүүд нэг нэг байрлал урагшилна.
    ```C
    void q_pop(Queue *p)
    ```

### Жагсаалт (List)

Жагсаалт бүтэц нь дурын байрлалд оруулаад, дурын байрлалаас гаргах боломжтой бүтэц.
Дараах функцүүд нь `List` бүтцийн гишүүдэд хандах буюу `p->l_arr[]` хүснэгт болон түүний элементүүдийн тоог хадгалах `p->l_len` хувьсагчидтай харьцана.
  * Төгсгөлд нь оруулах үйлдэл. p-ийн зааж буй List-д x утгыг төгсгөлд хийнэ.
    ```C
    void l_push_back(List *p, int x)
    ```
  * Эхлэлд нь оруулах үйлдэл. p-ийн зааж буй List-д x утгыг эхэнд хийнэ. `p->q_arr` хүснэгтийн бүх элементүүд нэг нэг байрлал хойшилно.
    ```C
    void l_push_front(List *p, int x)
    ```
  * Дурын байрлалд оруулах үйлдэл. p-ийн зааж буй List-д x утгыг pos байрлалд хийнэ. pos болон түүнээс хойшхи элементүүд нэг байрлал ухарна. Тухайн байрлал List-ийн сүүлийн индексээс их бол төгсгөлд орно.

    ```C
    void l_insert(List *p, int x, int pos)
    ```
  * Төгсгөлөөс нь гаргах үйлдэл. p-ийн зааж буй List-н төгсгөлөөс гаргана.
    ```C
    void l_pop_back(List *p)
    ```
  * Эхлэлээс нь гаргах үйлдэл. p-ийн зааж буй List-н эхлэлээс гаргана. List-ийн бүх элементүүд нэг нэг байрлал урагшилна.
    ```C
    void l_pop_front(List *p)
    ```
  * Дурын байрлалаас гаргах үйлдэл. p-ийн зааж буй List-н pos байрлалаас гаргана. pos болон түүнээс хойшхи элементүүд нэг байрлал урагшилна. pos байрлалаас гаргах боломжгүй бол юу ч хийхгүй.
    ```C
    void l_erase(List *p, int pos)
    ```
  * Жагсаалтаас хайх үйлдэл. p-ийн зааж буй List-с x тоог хайн олдсон байрлалаыг буцаана. Олдохгүй бол -1 утгыг буцаана.
    ```C
    int l_search(List *p, int x)
    ```