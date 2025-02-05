package fr.eni.projet.projeteni.dal;

import fr.eni.projet.projeteni.bo.Images;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public class ImagesDapSQLlmpl implements ImagesDao {

    private static final String SELECT_ALL = "SELECT * FROM images";
    private static final String SELECT_BY_ID = "SELECT * FROM images WHERE id = ?";
    private static final String INSERT = "INSERT INTO images (nom, description, image, content_type) " +
            "VALUES (:nom, :description, :images, :content_type)";
    private static final String UPDATE = "UPDATE images SET nom = ?, description = ?, images = ?, content_type = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM images WHERE id = ?";


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    public ImagesDapSQLlmpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }



    @Override
    public List<Images> readAll() {
        return jdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Images.class));
        }


    @Override
    public Images readById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, BeanPropertyRowMapper.newInstance(Images.class), id);
    }

    @Override
    public int create(Images images) {
        var namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom", images.getNom());
        namedParameters.addValue("description", images.getDescription());
        namedParameters.addValue("images", images.getImages());
        namedParameters.addValue("content_type", images.getContentType());

        var keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT, namedParameters, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(Images images) {
        jdbcTemplate.update(UPDATE,
                images.getNom(),
                images.getDescription(),
                images.getImages(),
                images.getContentType(),
                images.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE, id);
    }

}
