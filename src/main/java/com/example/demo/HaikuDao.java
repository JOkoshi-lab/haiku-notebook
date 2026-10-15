package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.Kuchou.HaikuItem;

@Service
public class HaikuDao {

    private final JdbcTemplate jdbcTemplate;

    public HaikuDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(HaikuItem item) {
        jdbcTemplate.update(
                "INSERT INTO haiku (id, haiku, poem_date, likes) VALUES (?, ?, ?, ?)",
                item.id(),
                item.haiku(),
                item.date(),
                item.likes());
    }

    public List<HaikuItem> findAll() {
        String sql = "SELECT id, haiku, poem_date AS \"date\", likes FROM haiku ORDER BY poem_date, id";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        return rows.stream()
                .map(row -> new HaikuItem(
                        row.get("id").toString(),
                        row.get("haiku").toString(),
                        row.get("date").toString(),
                        ((Number) row.get("likes")).intValue()))
                .toList();
    }

    public int change(String id) {
        return jdbcTemplate.update(
                "UPDATE haiku SET likes = likes + 1 WHERE id = ?",
                id);
    }
}