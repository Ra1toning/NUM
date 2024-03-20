import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface ProductFileInterface<T> {
    void addProduct(T product);
    void updateProduct(Long id, T newProduct);
    void deleteProduct(Long id);
    T findProductById(Long id);
    List<T> findProductByCompanyName(String companyName);
    List<T> findAllProduct();
}

class T implements Serializable {

    private Long id;
    private String name;
    private String Company;
    private double price;
    private String type;
    private String description;

    public T(Long id, String name, String Company, double price, String type, String description) {
        this.id = id;
        this.name = name;
        this.Company = Company;
        this.price = price;
        this.type = type;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public int hashCode() {
        final int p = 31;
        int result = 1;
        result = p * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object n) {
        if (this == n) return true;
        if (n == null || getClass() != n.getClass()) return false;
        T product = (T) n;
        return id.equals(product.id);
    }
    @Override
    public String toString() {
        return "Бүтээгдэхүүн: id=" + id + ", нэр=" + name + ", Үйлдвэрлэсэн Компани=" + Company + ", Үнэ=" + price
                + ", Төрөл=" + type + ", Бүтээгдэхүүний тайлбар=" + description;
    }
}

class ProductFile implements ProductFileInterface<T> {
    private final String filePath = "Products.dat";

    @Override
    public void addProduct(T product) {
        List<T> products = findAllProduct();
        products.add(product);
        ProductsToFile(products);
    }

    @Override
    public void updateProduct(Long id, T newProduct) {
        List<T> products = findAllProduct();
        int index = products.indexOf(products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null));
        if (index != -1) {
            products.set(index, newProduct);
            ProductsToFile(products);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        List<T> products = findAllProduct();
        products.removeIf(p -> p.getId().equals(id));
        ProductsToFile(products);
    }

    @Override
    public T findProductById(Long id) {
        return findAllProduct().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<T> findProductByCompanyName(String companyName) {
        return findAllProduct().stream().filter(p -> p.getCompany().equals(companyName)).collect(Collectors.toList());
    }

    @Override
    public List<T> findAllProduct() {
        List<T> products = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            T product;
            while ((product = (T) ois.readObject()) != null) {
                products.add(product);
            }
        } catch (EOFException eof) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    private void ProductsToFile(List<T> products) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (T product : products) {
                oos.writeObject(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Main {
    public static void main(String[] args) {
        ProductFileInterface<T> File = new ProductFile();

        //end shine product nemjiigaa
        File.addProduct(new T(1L, "Shiree", "Best Buidan XXK", 500000, "GER AHUI", "PC-nii shiree."));
        File.addProduct(new T(2L, "TV", "NEXT ELECTRONICS", 1200000, "electron baraa", "SMART TV"));
        File.addProduct(new T(3L, "RTX 4090", "NEXT ELECTRONICS", 4900000, "electron baraa", "GIGBYTE 24GB ramtai shinge gpu"));
        File.addProduct(new T(4L, "Pizza", "Jur Ur", 29500, "huns", "18IN hulduusun buteegdehuun"));

        //Product.dat-d hiisen buh productiin medeelliig delgetsend hevelnve
        System.out.println("---БҮХ БҮТЭЭГДЭХҮҮНҮҮД---");
        List<T> allProducts = File.findAllProduct();
        for (T product : allProducts) {
            System.out.println(product);
        }

        T foundProduct = File.findProductById(2L);
        if (foundProduct != null) {
            System.out.println("\nID: 2-р ХАЙЛ ХИЙХЭД: " + foundProduct);
        } else {
            System.out.println("Product not found by ID.");
        }

        //productaa update hiine. dahin compile hiihed soligdson ur dun nemegdene
        T updatedProduct = new T(1L, "Sandal", "Best Buidan XXK", 200000, "Ger ahui", "gaming sandal rgb asdag");
        File.updateProduct(1L, updatedProduct);

        //Company ner ni taarsan baraanii medeellig hevlene
        List<T> companyProducts = File.findProductByCompanyName("NEXT ELECTRONICS");
        System.out.println("NEXT ELECTRONICS-н бараа: " + companyProducts + "\n");

        // PRODUCT-aa delete hiine
        System.out.println("--DELETE ХИЙГДСЭНИЙ ДАРАА---");
        File.deleteProduct(2L); //2 id tai productiig delete hiine
        allProducts = File.findAllProduct();
        for (T p : allProducts) {
            System.out.println(p);
        }
    }
}
