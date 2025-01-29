package fr.eni.projet.projeteni.dal;

import fr.eni.projet.projeteni.bo.Enchere;
import fr.eni.projet.projeteni.bo.Enchere;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DaoEncheresSQLImpl implements DaoEncheres {

    static final String SELECT_ALL = "select * from ENCHERES";
    static final String SELECT_BY_ID = "select * from ENCHERES where no_article = ?";
    static final String INSERT = "INSERT  INTO ENCHERES ([no_utilisateur],[no_article],[date_enchere],[montant_enchere]) " +
            "VALUES (:no_utilisateur,:no_article,:date_enchere,:montant_enchere)";
    static final String DELETE = "DELETE FROM ENCHERES where no_article=?";
    static final String UPDATE = "UPDATE ENCHERES set no_enchere=?,no_article=?,date_enchere=?,montant_enchere=? where no_article=?";


    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private RowMapper<Enchere> rowMapper;

    public DaoEncheresSQLImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, RowMapper<Enchere> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.rowMapper = rowMapper;
    }
    @Override
    public Enchere read(int noArticle) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, rowMapper, noArticle);
    }


    @Override
    public List<Enchere> read() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

//    @Override
//    public Enchere read(int idArticle) {
//        return jdbcTemplate.queryForObject(SELECT_BY_ID, BeanPropertyRowMapper.newInstance(Enchere.class), idArticle);
//    }

    @Override
    public int create(Enchere enchere) {
        var namedparameters = new MapSqlParameterSource();
        namedparameters.addValue("no_utilisateur", enchere.getArticleVendu().getVendeur().getNoUtilisateur());
        namedparameters.addValue("no_article", enchere.getArticleVendu().getNoArticle());
        namedparameters.addValue("date_enchere", enchere.getDateEnchere());
        namedparameters.addValue("montant_enchere", enchere.getMontant_enchere());
        var keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT, namedparameters, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(Enchere enchere) {
        jdbcTemplate.update(UPDATE, enchere.getArticleVendu().getNoArticle(), enchere.getArticleVendu().getNoArticle(), enchere.getDateEnchere(), enchere.getMontant_enchere());
    }

    @Override
    public void delete(Enchere enchere) {
        jdbcTemplate.update(DELETE, enchere.getArticleVendu().getNoArticle());
    }


}
