package fr.eni.projet.projeteni.bo;

public class Images {

    private int id;
    private String nom;
    private String description;
    private byte[] images;
    private String contentType;


    public Images() {
    }

    public Images(int id, String contentType, byte[] images, String description, String nom) {
        this.id = id;
        this.contentType = contentType;
        this.images = images;
        this.description = description;
        this.nom = nom;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public byte[] getImages() { return images; }
    public void setImages(byte[] images) { this.images = images; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
}
