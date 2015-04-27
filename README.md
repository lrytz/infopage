# Install Tools

```
brew install sbt
brew install mongodb
brew install ruby
gem install jekyll
npm install -g bower
```

# Install or Update Dependencies

```
bower install
./copy-deps
```

# Run

mongodb
```
mongod --config /usr/local/etc/mongod.conf
```

server
```
sbt stage
./target/universal/stage/bin/infopage-server
```

website
```
jekyll build # or jekyll serve
```

# Data

wipe
```
curl -X POST 127.0.0.1:8080/wipeOutSubmissions
```

export:
```
curl http://127.0.0.1:8080/submissionsToCVS
```


# Notes

Doc
  - http://getbootstrap.com/components/#navbar
  - http://sass-lang.com/guide
  - https://github.com/Shopify/liquid/wiki/Liquid-for-Designers
  - http://jekyllrb.com/docs/home/
  - https://github.com/ryun/HCaptions


Grundsätzlich
  - Index: Grosse Buttons
  - 
  - Alle Seiten im ähnlichen Schema




Seiten
  - Fliegen Lernen!
    - Ausbildung Segelflug
    - Ausbildung Motoflug
    - Schnupperflüge
    - Fliegen als Beruf

  - Über die Flugschule Grenchen
    - Verein
    - Vermietung
    - Passagierflüge

  - Unsere Flotte im Segelflug
    - Zuerst: Bilder nebeneinander, bei Klick modaler Dialog mit Details
    - Pro Flugzeug: Bild, 1-2 Sätze, Tabelle mit Daten (PS, Reisegeschwindigkeit, Range, MTOM)

  - Unsere Flotte im Motorflug

  - Videos / Fotos?

  - Wettbewerb