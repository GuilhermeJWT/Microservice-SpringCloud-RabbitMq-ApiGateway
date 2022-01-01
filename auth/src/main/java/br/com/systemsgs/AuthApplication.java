package br.com.systemsgs;

import br.com.systemsgs.model.ModelPermission;
import br.com.systemsgs.model.ModelUser;
import br.com.systemsgs.repository.PermissionRepository;
import br.com.systemsgs.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository,
						   BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(userRepository, permissionRepository, passwordEncoder);
		};

	}

	private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository,
						   BCryptPasswordEncoder passwordEncoder) {

		ModelPermission permission = null;
		ModelPermission findPermission = permissionRepository.findByDescription("Admin");
		if (findPermission == null) {
			permission = new ModelPermission();
			permission.setDescription("Admin");
			permission = permissionRepository.save(permission);
		} else {
			permission = findPermission;
		}

		ModelUser admin = new ModelUser();
		admin.setUserName("guilherme");
		admin.setAccountNonExpired(true);
		admin.setAccountNonLocked(true);
		admin.setCredentialsNonExpired(true);
		admin.setEnabled(true);
		admin.setPassword(passwordEncoder.encode("123456"));
		admin.setPermissions(Arrays.asList(permission));

		ModelUser find = userRepository.findByUserName("guilherme");
		if (find == null) {
			userRepository.save(admin);
		}
	}

}
