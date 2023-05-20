package MintCulture.Server.TestPackage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BreadRepository extends JpaRepository<Bread,Long> {
    // JpaRepository<Entity,Id>
}