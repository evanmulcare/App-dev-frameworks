package ie.spring.jdbc.repo;

import ie.spring.jdbc.entites.Cartoon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartoonRowMapper implements RowMapper<Cartoon> {
    @Override
    public Cartoon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cartoon cartoon = new Cartoon();
        cartoon.setCartoonId(rs.getInt("cartoonId"));
        cartoon.setName(rs.getString("name"));
        cartoon.setFirstAppearanceYear(rs.getInt("firstAppearanceYear"));
        return cartoon;
    }
}