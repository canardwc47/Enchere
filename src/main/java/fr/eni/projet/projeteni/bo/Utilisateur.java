package fr.eni.projet.projeteni.bo;

import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.*;


public class Utilisateur {

    private int noUtilisateur;
    @NotBlank(message = "Le pseudo ne peut pas être vide")
    @Size(min = 1, max = 10, message = "Le pseudo doit contenir entre 1 et 10 caractères")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Le pseudo ne peut contenir que des caractères alphanumériques, des tirets ou des underscores")
    private String pseudo;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Le nom ne doit contenir que des lettres et des espaces")
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide")
    @Size(max = 100, message = "Le prénom ne peut pas dépasser 100 caractères")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Le prénom ne doit contenir que des lettres et des espaces")
    private String prenom;

    @NotBlank(message = "L'email ne peut pas être vide")
    @Size(max = 254, message = "L'email ne peut pas dépasser 254 caractères")
    @Email(message = "Format d'email invalide")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Le numéro de téléphone doit contenir entre 10 et 15 chiffres avec un éventuel '+' au début")
    private String telephone;

    @NotBlank(message = "La rue ne peut pas être vide")
    @Size(max = 255, message = "La rue ne peut pas dépasser 255 caractères")
    private String rue;

    @Min(value = 10000, message = "Le code postal doit être un nombre à 5 chiffres")
    @Max(value = 99999, message = "Le code postal doit être un nombre à 5 chiffres")
    private int codePostal;

    @NotBlank(message = "La ville ne peut pas être vide")
    @Size(min = 2, max = 50, message = "La ville doit contenir entre 2 et 50 caractères")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\- ]+$", message = "Le nom de la ville ne peut contenir que des lettres, des espaces et des tirets")
    private String ville;

    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    @Size(min = 8, max = 64, message = "Le mot de passe doit contenir entre 8 et 64 caractères")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,64}$",
            message = "Le mot de passe doit contenir au moins une majuscule, une minuscule, un chiffre et un caractère spécial")
    private String motDePasse;

    @PositiveOrZero(message = "Le crédit ne peut pas être négatif")
    private int credit;
    private List<ArticleVendu> articleVendus = new ArrayList<ArticleVendu>();
    private List<ArticleVendu> articleAchetes = new ArrayList<ArticleVendu>();
    private List<Enchere> encheres = new ArrayList<Enchere>();

    public Utilisateur() {
    }

    public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, int codePostal, String ville, String motDePasse, int credit, List<ArticleVendu> articleVendus, List<ArticleVendu> articleAchetes, List<Enchere> encheres) {
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.articleVendus = articleVendus;
        this.articleAchetes = articleAchetes;
        this.encheres = encheres;
    }

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public List<ArticleVendu> getArticleVendus() {
        return articleVendus;
    }

    public void setArticleVendus(List<ArticleVendu> articleVendus) {
        this.articleVendus = articleVendus;
    }

    public List<ArticleVendu> getArticleAchetes() {
        return articleAchetes;
    }

    public void setArticleAchetes(List<ArticleVendu> articleAchetes) {
        this.articleAchetes = articleAchetes;
    }

    public List<Enchere> getEncheres() {
        return encheres;
    }

    public void setEncheres(List<Enchere> encheres) {
        this.encheres = encheres;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "noUtilisateur=" + noUtilisateur +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal=" + codePostal +
                ", ville='" + ville + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", credit=" + credit +
                ", articleVendus=" + articleVendus +
                ", articleAchetes=" + articleAchetes +
                ", encheres=" + encheres +
                '}';
    }
}
