# 2-Keycloak-Installation

İki yöntemle keycloak kullanabiliriz. Birincisi local’de çalıştırmak, ikincisi container olarak çalıştırmak.

1- Local’de çalıştırma

- [https://www.keycloak.org/downloads](https://www.keycloak.org/downloads) linkinden tar.gz dosyasını indiriyoruz.
- tar -xvzf indirilen dosya  >> komutu ile rar dan çıkartıyoruz.
- /bin klasörü içinde [standalone.sh](http://standalone.sh/) dsoyasını “./[standalone.sh](http://standalone.sh/)” komutu ile çalıştırıyoruz.
- browserdan [http://localhost:8080/auth/](http://localhost:8080/auth/) linki ile giriş sayfasına geliyoruz.
- Username ve şifre oluşturuyoruz.
- Administration console’a tıklayarak şifremizi girip sisteme giriş yapıyoruz.

Bu yöntemde default olarak 8080 portu kullanılır bunu değiştirmek için 2 yöntem mevcut. 

- standalone dosyası çalıştırırken aşağıdaki gibi offset parameresi verilebilir.`standalone.sh -Djboss.socket.binding.port-offset=100`  >> 8180 den başlatır.
- keycloak-16.1.0/standalone/configuration/standalone.xml içine girip aşağıdaki resimdeki ilgili kısım değiştirilebilir.

![Untitled](2-Keycloak-Installation%205d968d51a6de47969152684780701fe6/Untitled.png)

2- Docker Container olarak başlatmak.

Aşağıdaki komut ile başlatılır. -p parametresi sonrası ilk değer yayın yapmak istenile port’tur.

`docker run -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:16.1.0`