package MintCulture.Server.UserPackage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
   User findByState(UserState state);
}
