package com.musicshop.repository;

import com.musicshop.model.BaseModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryBaseRepository<T extends BaseModel<Long>> implements Repository<T, Long> {

    private final Map<Long, T> storage = new HashMap<>();
    private long currentId = 1L;

    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            entity.setId(currentId++);
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
}
