package ie.spring.jdbc.repo;

import ie.spring.jdbc.entites.Cartoon;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CartoonRepoImpl implements CartoonRepo {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM cartoons";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<Cartoon> findAll() {
        String sql = "SELECT * FROM cartoons";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cartoon.class));
    }

    @Override
    public Cartoon findByCartoonId(int id) {
        String sql = "SELECT * FROM cartoons WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CartoonRowMapper(), id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        String sql = "DELETE FROM cartoons WHERE cartoonId = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbcTemplate.update(sql, params);
    }

}
