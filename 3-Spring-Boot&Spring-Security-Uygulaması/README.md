# b-Kurulum bilgilendirme

Bu örnek projede keycloak kullanmadan authentication ve authorization nasıl yapılır onu görmüş olacağız. 

### Kurulması gereken paketler;

- JDK
- STS (spring tool suite)
- MySQL

### Spring Security & Thymeleaf

Spring Security, Spring Framework’ü ile Authorization ve Authentication sağlayan bir yöntemdir. Bir sonraki adımda bu işlemi keycloak ile yapacağız.

Thymeleaf, Java tabanlı HTML templete engine’dir. HTML dosyaları resource/templetes altına eklenir. Bu sayede frontend için extra framework kullanmadan çalışmamızı görmüş olacağız.

STS programını açalım ve aşağıdaki adımları izleyerek yeni bir proje başlatalım.

![Untitled](b-Kurulum%20bilgilendirme%208925daab7b0f422d9b2668bf30e7a1cd/Untitled.png)

![Untitled](b-Kurulum%20bilgilendirme%208925daab7b0f422d9b2668bf30e7a1cd/Untitled%201.png)

Sonraki sayfada aşağıdaki dependecy’leri ekleyelim.

![Untitled](b-Kurulum%20bilgilendirme%208925daab7b0f422d9b2668bf30e7a1cd/Untitled%202.png)

src/main/java altına controller, service, entity, repository ve security java package’ları ekleyelim.

- com.doka.controller
- com.doka.service
- com.doka.security
- com.doka.entity
- com.doka.repository

Başlangıç adımları yukarıda anlatıldığı gibi olan projenin dosyaları repoda mevcuttur. Kodlar tek tek anlatılmayacaktır.