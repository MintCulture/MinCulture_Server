package MintCulture.Server.Test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BreadRepository extends JpaRepository<Bread,Long> {
    // JpaRepository<Entity,Id>
}