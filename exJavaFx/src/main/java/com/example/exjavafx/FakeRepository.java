package com.example.exjavafx;

import java.util.List;

public class FakeRepository implements ProductRepository{

    @Override
    public List<Product> findAll() {
        return List.of();
    }
}
