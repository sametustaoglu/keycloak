# Keycloak-Proje

## a. Keycloak-MySQL configürasyon ayarlamaları

Keycloak default olarak embeded  H2 database’ini kullanır. Biz mysql kullanmak için bazı işlemler yapmamız gerekir

java mysql connector programını platform dependent olarak indir. Aşağıdaki klasöre jar dosyasını yapıştır.

`keycloak-16.1.0/modules/system/layers/keycloak/com/mysql/main`

main klasörünün içerisine içeriği aşağıdaki gibi olacak şekilde module.xml oluştur. Dokümantasyonda mevcut postresql için örneği.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.3" name="com.mysql">

    <resources>
        <resource-root path="mysql-connector-java-8.0.27.jar"/>
    </resources>

    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
```

keycloak-16.1.0/standalone/configuration/stanalone.xml dosyasında aşağıdaki bloğu bul.

```xml
</datasource>
                <datasource jndi-name="java:jboss/datasources/KeycloakDS" pool-name="KeycloakDS" enabled="true" use-java-context="true" statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
                    <connection-url>jdbc:h2:${jboss.server.data.dir}/keycloak;AUTO_SERVER=TRUE</connection-url>
                    <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                        <password>sa</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                  
```

Yukarıdaki bloğu aşağıdaki şekilde olduğu gibi değiştir. Connection-URl değiştiriyoruz ve yeni bir driver ekliyoruz.

```xml
</datasource>
                <datasource jndi-name="java:jboss/datasources/KeycloakDS" pool-name="KeycloakDS" enabled="true" use-java-context="true" statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
                    <connection-url>jdbc:mysql://localhost:3306/keycloak</connection-url>
                    <driver>mysql</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>12345678</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                    <driver name="mysql" module="com.mysql">
                        <driver-class>com.mysql.jdbc.Driver</driver-class>
                    </driver>
                </drivers>
            </datasources>
```

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled.png)

Değişiklikler yukarıdaki resimde işaretlenmiştir.

keycloak ismi mysql de oluşturduğumuz database ismi olacak. Yani daha önce içi boş bir database oluşturmak gerekiyor. İsmini de url kısmına ekliyoruz.

keycloak’ı çalıştırdığımızda database’i takip edersek default table’ların oluştuğunu görebiliriz.

---

## b. Keycloak arayüzü üzerinden yapılacak işlemler

[localhost:8080](http://localhost:8080) ile giriş yap. Kullanıcı adı şifre belirle.

### Realm & Client Nedir?

**Realm**, user’lar, role’ler, credential’ların bulunduğu merkezi yerdir. Her realm birbirinden ayrılmıştır.

Keycloak’ı kullanmak isteyen her bir app de **client**’dır. Keyklock server’a authentication için istekte bulunan entity’ler de diyebiliriz.

I**dentity providers:** Farklı tipte identity providerlar(open id connect, saml 2.0, social(github, google vb)) keycloak destekler. Bunların haricinde harici veritabanları da kullanılabilir.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%201.png)

user federation: ldap ve kerberos destekler. İstenirse herhangi bir data seçilip özel connector create edilebilir.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%202.png)

Authentication: Farklı tipte Authentication flow lar oluşturulabilir. 

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%203.png)

Sol konsol alt kısımda user, grup, seesion yönetim bölümü bulunur. Daha altında event log lar

bulunur ve farklı konfigurasyonları, user, realm import export seçeneği mevcuttur.

### Realm & Client Oluşturma

Cursor’ı Master yazısına getirdiğinde aşağıdaki resimi göreceksiniz. add realm ile yeni bir realm oluşturacağız.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%204.png)

Aşağıdaki ilgili kısma isim girip create diyoruz.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%205.png)

Aşağıdaki resme baktığımızda realm değiştiğini görebiliyoruz. Client kısmına gelip yeni bir client oluşturacağız. Client için keycloak’ı kullanmak isteyen app’ler demiştik. 2 tane client ekleyeceğiz student-app ve professor-app.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%206.png)

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%207.png)

student-app client oluştuktan sonra setting kısmından aşağıdaki resimdeki ayarları yapalım.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%208.png)

Access type default olarak public gelmekte. Ama biz athorization için confidential seçiyoruz ki redirect ettiğimiz url keycloak server tarafından doğrulansın.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%209.png)

redirect URl için student-app çalışan port bilgileriyle yazıyoruz.

Bu bilgilerle kaydettikten sonra aynı sayfada Credentials menüsünde client secret oluştuğu görülür. Bu değeri daha sonra spring boot application’lar da kullanacağız.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2010.png)

Yukarıda yapıla tüm işlemler diğer client’ımız olan professor-app için de yapalım.

Şuana kadar yaptığımız bir realm eklemek ve 2 client oluşturmak oldu. Şimdi de user oluşturacağız.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2011.png)

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2012.png)

User create ettikten sonra aynı sayfada credential menüsünden password belirleyelim.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2013.png)

Temporary, john ilk girişte password değiştirmek zorunda kalsın diye açık best practise. Alt kısımda set password butonuna basalım. Aynı işlemi diğer user sachin içinde yapalım. 2 adet user oluştu.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2014.png)

Şimdi de role oluşturup user’lara atanmasını yapalım.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2015.png)

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2016.png)

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2017.png)

Rolleri oluşturduk şimdi user’lara rolleri atayalım. İlgili user’a gelelim.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2018.png)

Role mapping menüsünden john için STUDENT rolünü ekledik. Sachin için de proffesor rolünü seçilim yanı şekilde. Bitirdikten sonra role menüsünden hangi rolde hangi user’lar atanmış görebiliyoruz.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2019.png)

Burada keycloak tarafı bitmiş oluyor şimdi Spring boot application’ımıza gidelim oradaki ayarlamaları yapalım.

---

## c. Spring Boot üzerinden yapılacak işlemler

Bu bölümde daha önce spring boot ile hazırlanan projeyi keycloak kullanarak ayağa kaldıracağız. Aynı proje dosyalarını alalım bunlar üzerinde değiştirme, ekleme ve silme yapacağız.

---

student-app pom.xml dosyasına aşağıdaki dependency’i ekleyelim.

```xml
<dependency>
			<groupId>org.keycloak</groupId>
			<artifactId>keycloak-spring-boot-starter</artifactId>
			<version>15.0.2</version>
		</dependency>
```

Daha sonra [application.properties](http://application.properties) dosyasına gelelim ve aşağıdaki şekilde güncelleyelim.

```xml
spring.datasource.url=jdbc:mysql://localhost:3306/university
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.database=mysql

server.port=8081

keycloak.auth-server-url=http://localhost:8080/auth
keycloak.realm=Doka
keycloak.resource=student-app
keycloak.credentials.secret=yh3DLIXyYgxBnGkLLb4nhERFAzJlXvpG
```

Yukarıdaki son dört satırı kendi keycloak ayarlarımıza göre düzenliyoruz.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2020.png)

Tekrar spring boot app’imize dönelim. [com.doka.security](http://com.doka.security) pakage içerisine yeni bir java sınıfı oluşturacağız. İsmi KeycloakSecurityConfig olsun. Bitirdiğimizde daha önceki bazı sınıfları sileceğiz. Dosya içeriği aşağıdaki gibi olacak.

```java
package com.doka.security;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

	@Autowired
	public void configureGloabl(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(keycloakAuthenticationProvider());
	}
	
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
	}
	
	@Bean
	KeycloakConfigResolver keycloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.authorizeRequests().antMatchers("contact-us").permitAll()
		.anyRequest().authenticated()
		.and().exceptionHandling().accessDeniedPage("/access-denied");
	}

}
```

contact-us.html sayfası hariç tüm sayfalar keycloak tarafından authorize olacak. Yani contact-us sayfasına herkes ulaşırken home sayfasına ulaşmak istenildiğinde keycloak server’a authontice olmak gerekecek. Artık login sayfası da olmayacak çünkü giriş keycloak tarafında olacak.

[StudentController.java](http://StudentController.java) dosyası içeriği de aşağıdaki gibi değişecek.

```java
package com.doka.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

	@GetMapping("/contact-us")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("contact-us");
		return modelAndView;
	}
	
	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("home");
		return modelAndView;
	}
	
	@GetMapping("/manage-students")
	@PreAuthorize("hasAuthority('PROFESSOR')")
	//@PreAuthorize("hasAuthority('PROFESSOR') or hasAuthority('STUDENT')")
	public ModelAndView manageStudents() {
		ModelAndView modelAndView = new ModelAndView("manage-students");
		return modelAndView;
	}
	
	@GetMapping("/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView modelAndView = new ModelAndView("access-denied");
		return modelAndView;
	}
}
```

Security package altındaki SpringSecurityConfig.java, [UserDetailsServiseImp.java](http://UserDetailsServiseImp.java), entity package altındaki [Roles.java](http://Roles.java) ve [User.java](http://User.java), repository package atındaki [RolesRepository.java](http://RolesRepository.java) ve [UserRepository.java](http://UserRepository.java) dosyaları artık gerekli değil silinebilir veya comment edilebilir. Buradaki işlemler keycloak arayüzü ile yaptığımız işlemlere tekabül etmekte.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2021.png)

contact-us sayfasına herhangi bir username şifre girmeden ulaştık.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2022.png)

[localhost:8081](http://localhost:8081)/home student app’e ulaşmak istediğimizde bizi keycloak’a yönlendirdi 8080’e. Username ve password girdikten sonra da şifre yenilememizi isteyecek temprory seçtiğimiz için.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2023.png)

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2024.png)

Yukarıdaki resmi alabilmek için 2 değişiklik daha gerekiyor. Birincisi [application.properties](http://application.properties) dosyasına `keycloak.principal-attribute=preferred_username`  kısmını ekleyip. home.html den ilgili satırda `.getUsername()` kısmını silerek, aşağıdaki gibi düzeltmek gerekecek aksi taktirde isim yerine user id karşılama sayfasında olacaktır.

```java
<h2 class="form-signin-heading">Welcome <span th:text = "${#authentication.getPrincipal()}"></span></h2>
```

8081 student app te iken [localhost:8081/manage-students](http://localhost:8081/manage-students) sayfasına ulaşmak istersek de yetkili olmadığımız için aşağıdaki hatayı alacağız. Çünkü [StudentController.java](http://StudentController.java) dosyasında aşağıdaki kodu yazmıştık.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2025.png)

Keycloak’da biz student-app’e sadece STUDENT  rolü verdiğimiz için ulaşamayacak.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2026.png)

Student-app için yaptığımız işlemleri professor-app için de yapalım.

---

Şimdi de Keycloak’da SSO nasıl çalışıyor ona bakalım. studen-app ve professor-app çalışıyor. 

Öncelikle incognito pencere açıp 8082/professor/home sayfasından sachin user olarak giriş yapalım.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2027.png)

Aynı browser’da sachin user ile student-app’e girmeye çalışalım.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2028.png)

Dikkat ederseniz kullanıcı adı şifre girmeme gerek kalmadı SSO mantığı bu şekilde aynı realm’de farklı client’lar için ayrı ayrı login olmaya gerek kalmıyor. İlk başlarda örnek verirken google üzerinden vermiştim. Gmail’e giriş yaptıktan sonra google drive veya youtube’a ulaşmak isterseniz tekrar login olmanız gerekmez.

![Untitled](Keycloak-Proje%20bca78ac41ed04ad5a8eebac68cc38df7/Untitled%2029.png)