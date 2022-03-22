package pizza.zidahi.ma.starsrate.classe;

public class Stars {
private String nom;
private int id;

    private String image;
    private float star;
    private static int compteur;

    public Stars(String nom, String image,float star) {
        this.nom = nom;
        this.id=++compteur;
this.star=star;
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Stars{" +
                "nom='" + nom + '\'' +
                ", id=" + id +
                ", image='" + image + '\'' +
                ", star=" + star +
                '}';
    }
}
