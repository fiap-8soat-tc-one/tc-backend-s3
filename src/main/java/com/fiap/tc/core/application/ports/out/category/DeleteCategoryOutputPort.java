package com.fiap.tc.core.application.ports.out.category;

import java.util.UUID;

public interface DeleteCategoryOutputPort {
    void delete(UUID uuid);
}
