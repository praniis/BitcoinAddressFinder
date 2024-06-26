# BitcoinAddressFinder
[![CI Status](https://github.com/bernardladenthin/BitcoinAddressFinder/actions/workflows/assembly.yml/badge.svg)](https://github.com/bernardladenthin/BitcoinAddressFinder/actions/workflows/assembly.yml)
[![CI Status](https://github.com/bernardladenthin/BitcoinAddressFinder/actions/workflows/coverage.yml/badge.svg)](https://github.com/bernardladenthin/BitcoinAddressFinder/actions/workflows/coverage.yml)
[![CI Status](https://github.com/bernardladenthin/BitcoinAddressFinder/actions/workflows/matrixci.yml/badge.svg)](https://github.com/bernardladenthin/BitcoinAddressFinder/actions/workflows/matrixci.yml)
[![Coverage Status](https://coveralls.io/repos/github/bernardladenthin/BitcoinAddressFinder/badge.svg?branch=main)](https://coveralls.io/github/bernardladenthin/BitcoinAddressFinder?branch=main)
[![codecov](https://codecov.io/gh/bernardladenthin/BitcoinAddressFinder/graph/badge.svg?token=RRCR4ZC28T)](https://codecov.io/gh/bernardladenthin/BitcoinAddressFinder)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fbernardladenthin%2FBitcoinAddressFinder.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fbernardladenthin%2FBitcoinAddressFinder?ref=badge_shield)

<!--
[![Security Score](https://snyk-widget.herokuapp.com/badge/mvn/net.ladenthin/bitcoinaddressfinder/badge.svg)](https://snyk.io/test/github/bernardladenthin/BitcoinAddressFinder)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.ladenthin/bitcoinaddressfinder/badge.svg#)](https://maven-badges.herokuapp.com/maven-central/net.ladenthin/bitcoinaddressfinder)
[![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/1234/badge)](https://bestpractices.coreinfrastructure.org/projects/1234)
-->
Free high performance tool for fast scanning random Bitcoin, Bitcoin Cash, Bitcoin SV, Litecoin, Dogecoin, Dash, Zcash (and many more) private keys and finding addresses with balance.
The main goal is to generate as fast as possible (Bitcoin/Altcoin) addresses using the JVM combined with OpenCL and check if the address (RIPEMD160 hash) was used/not used before. This includes possible hash collisions.

Copyright (c) 2017-2024 Bernard Ladenthin.

## Requirments
* Java 21 or newer. Java 8, 11, 17 is not supported anymore.

## Quickstart
1. Download the binary (jar) from https://github.com/bernardladenthin/BitcoinAddressFinder/releases
2. Download and extract the light database from https://github.com/bernardladenthin/BitcoinAddressFinder#use-my-prepared-database
3. Download a configuration set like
  1. https://github.com/bernardladenthin/BitcoinAddressFinder/blob/main/examples/logbackConfiguration.xml
  2. https://github.com/bernardladenthin/BitcoinAddressFinder/blob/main/examples/config_Find_1OpenCLDevice.js
  3. https://github.com/bernardladenthin/BitcoinAddressFinder/blob/main/examples/run_Find_1OpenCLDevice.bat
4. Put all in one directory like the following structure
  * Downloads
    * lmdb
      * data.mdb
      * lock.mdb
    * bitcoinaddressfinder-1.3.0-SNAPSHOT-jar-with-dependencies.jar
    * logbackConfiguration.xml
    * config_Find_1OpenCLDevice.js
    * run_Find_1OpenCLDevice.bat
5. Run the file run_Find_1OpenCLDevice.bat

## Features
* Support blockchain addresses which are based on [secp256k1](https://en.bitcoin.it/wiki/Secp256k1).
* Unit tested (trusted) open source which can be compiled easily from yourself.
* Vanitygen of bitcoin addresses using regex pattern.
* Runs completely offline. No internet required or used. You can run it in a bunker with an electric generator somewhere in nowhere and nobody knows it.
* No synchronisation necessary to run multiple instances. Random numbers are used and a search organization is not necessary. Just start on multiple computers.
* Check with a high performance database containing addresses if generated address are already in use.
* Portable, plattform independend, runs on JVM.
* Generate uncompressed and compressed keys at once.
* EC-Key generation via
  * Multiple CPU Threads
  * Multiple OpenCL devices (optional)

## Address database
The addresses will be inserted in a high performance database [LMDB](https://github.com/LMDB).
The database can be used to check if a generated addresses is ever used.

### Import
The importer read multiple txt/text files containing the following addresses in arbitrary order. Each line can contain a different format.
* P2PKH
  * bitcoin
  * bitcoin cash
  * bitcoin gold
  * blackcoin
  * dash
  * digibyte
  * dogecoin
  * feathercoin
  * litecoin
  * litecoin cash
  * namecoin
  * novacoin
  * reddcoin
  * vertcoin
  * ZCash
* P2WPKH
  * bitcoin Bech32

### Create the database by yourself
Useful txt/text file provider:
* http://blockdata.loyce.club/alladdresses/
* https://blockchair.com/dumps

### Export
The exporter writes all addresses in different formats:
* HexHash: The hash160 will be written encoded in hex without the amount. Optimal viewing with a viewer with a fixed width (e.g. HxD).
* FixedWidthBase58BitcoinAddress: The addresses will be written with a fixed width and without the amount. Optimal viewing with a viewer with a fixed width (e.g. HxD).
* DynamicWidthBase58BitcoinAddressWithAmount: The addresses will be written with amount.

### Use my prepared database
I am in the process of preparing databases filled with numerous Bitcoin and altcoin addresses (refer to the Import Support section for more information).
The sources of this information are confidential; however, you have the permission to extract any and all addresses.
Should there be any information you find lacking or have questions about, do not hesitate to ask.

#### Light database
* Light (5.25 GiB), Last update: March 22, 2024
  * Contains Bitcoin addresses whith amount and many altcoin addresses with amount.
  * Static amount of 0 is used to allow best compression.
  * Unique entries: 119509048
  * Mapsize: 5376 MiB
  * Time to create the database: ~9 hours
  * Link (3.3 GiB zip archive): http://ladenthin.net/lmdb_light.zip
  * Link extracted addresses as txt (2.1 GiB zip archive); open with HxD, set 42 bytes each line: http://ladenthin.net/LMDBToAddressFile_Light_HexHash.zip

<details>
<summary>Checksums lmdb_light.zip</summary>

* CRC32: 3F1FDC9A
* MD5: 1F9776BD28ED26C1DA8D7B00DC387A2B
* RipeMD160: 8D1993B1CF022B940DB5AAA5C22CDDF816DE6CD1
* SHA-1: 95D907CED30F473428B37A59E6C9C8EDADCDD633
* SHA-256: A25A19966645C8D2F22CCA3478980AFBEE1964409C3744684241EFE5ED2DABC1
* SHA-512: 1F63C07CFB6287D87CEBE175BA1710041D133EA3B4A4A445F4BF155A00E1289A09B7EFC4A40F215C032A0D25EE357AE15E9DD3FB0C649C02149372877A9E9202
* SHA3-224: E23E6DA501C84B09C050A4EBEE255427EAAF2D2D0A9773355B055FEC
* SHA3-256: B575839DE86CEC95ACD370636CCAE35D463873F2A3A7D30296BBB9363E9AC659
* SHA3-384: B50DC230430187AC612AECFC64952129C2D54B6DE1BDBFCD239754F66D9CC2CB86A4C9DEBC196886C7B9B309A6F0439D
* SHA3-512: 75AA1D6B9379F4AAA6F01B11BE9F94B59173604EA89BAA9D4F15E3DAFAB87B9ACDDA76BB102EB83C20BA4FED85172B6D7D843EE93678C232395B1E586D23AE55

</details>

<details>
<summary>Checksums LMDBToAddressFile_Light_HexHash.zip</summary>

* CRC32: CDF4DEAA
* MD5: A3C18E19D12B661A52EABE3D9C3C98A5
* RipeMD160: 86351035F7DF951E3BBFED6F0A1DD6E393E1A503
* SHA-1: DAC3028774CA60EA34EA6C698DF18AEB1E3736FB
* SHA-256: A47C074CEEDEE88C3DB4660B0A6668431195602248E11843ADC1170DB115EBE1
* SHA-512: 5B135919C96782927170CC6675DA2B28130435B896B985D4406BDC7BBD64FE402362F9AD8CDE70F2DAE39D4A4500956BA76A9E40D19FAB8225FB6D9F919D006A
* SHA3-224: F01CCFEED3775888569A9D36A0F456F3EF2251852771202D353081E7
* SHA3-256: 8430D6F48E2D5BFD1544587AA423EC41764EE14FD619804DC6C89CE1A3D328F4
* SHA3-384: F8A4A5AE2577B83F56E363C9C82D58EB782E8BCCDDEB8B92416F0A9C44438B268333D2B7B0BBE4A4ABFC35CDC1222FA7
* SHA3-512: B7D69667D5AC44F5A5F7354E9C4AA34D662D837119B10C394C04A93B6F8C117F82F2F8BE576617FE7B4F940E71001949DEF1B59EC79FC920FF011050B3943B75

</details>


#### Full database
* Full (55.0 GiB), Last update: March 22, 2024
  * Contains all Bitcoin addresses which are ever used and many altcoin addresses with and without amount.
  * Static amount of 0 is used to allow best compression.
  * Unique entries: 1251129601
  * Mapsize: 56048 MiB
  * Time to create the database: ~54 hours
  * Link (29.7 GiB zip archive): http://ladenthin.net/lmdb_full.zip
  * Link extracted addresses as txt (21.2 GiB zip archive); open with HxD, set 42 bytes each line: http://ladenthin.net/LMDBToAddressFile_Full_HexHash.zip

<details>
<summary>Checksums lmdb_full.zip</summary>

* CRC32: AC705AEA
* MD5: DFCE421B8C7E720DC216175598B20975
* RipeMD160: 7ABF5F9B6F432B0E6628A764CE58EDE8096692B6
* SHA-1: D6EDF234989DE68E241EB3ACE717FBD2E8EABC07
* SHA-256: 5932172C515CB632B92DD0C91FDA518D3A5BD2DE6375DE4C5BF480731663FEF7
* SHA-512: 1944F4F41BDCD3B6117A77F584EF76B784CD9931A476668163C8AD0C77422B014DFBF5C1DBD1EF79D73397BF58AD654CB12DD5011762D78F18C5D7A1A3D4271A
* SHA3-224: B41A7FF86488577476F65CBF3C0223003210D2FF50E71BC0C8CF5AAD
* SHA3-256: C39C4E926502BB19737E07C803F9DDC0A0BE7AAB9AA5E98BE1659F17ECA2AEF0
* SHA3-384: 6B37F3E1C4F9BD265B1B622EB8BD8270E6F66F08A0F56D1F20DC2863A57ED93F99F15ACDF2A77CC1DAB748DD9334081B
* SHA3-512: A2453833006494923343A961AD23483D8CA9E7F3065A525A89CC05B6B541C195E09C3EBF1F1F5EB5513FE1AC1B025D57FE577C9D434807A2A89F166A2902072E

</details>

<details>
<summary>Checksums LMDBToAddressFile_Full_HexHash.zip</summary>

* CRC32: F213E215
* MD5: 27167D44969DCB86E20F466E4FAF1066
* RipeMD160: B92B61E1C2B3C0344A6622C59FEDD3C1A699611E
* SHA-1: DE9FB5637650601CB669359F14D6F4C8EA8050AC
* SHA-256: E2485F2833740EFE3C4687E6B7A5921B02CD7D83AD7EEBFB600C6F4286A279F0
* SHA-512: 44D70EA70BAFF60880E625679A4AD0B5D38C82DF332D21BD58B93D87F487946DD197F095AEC619FAE005224D382E5E1C21344441C8995A09291A1BF2C60B3820
* SHA3-224: 7D4E4F40CE6EB8C5449DEC1317826228C788B92ABA31FA6E4AC76F27
* SHA3-256: E1B31B8E408FF6BD128FBFFFE2C6C9186E251EB084752F97FAA80A462EF8651C
* SHA3-384: DA0CA34D845F8B508287FD5E3B2B9CA6398F866727B67F8E353ECFC7A8B5747B74AFC0343EA0E912BECB212AA084701D
* SHA3-512: 121F37DB04ECEB1496BB09C219E1B1B9069B5AE490D9D8B04695BAF4C61377ABB4AE648F0037B3061F2942916FF74198C0854F7950892B7D9D119C6144384CE8

</details>

## Pages and projects to get lists (dumps) of PubkeyHash addresses
* https://github.com/Pymmdrza/Rich-Address-Wallet
* https://github.com/mycroft/chainstate
* https://github.com/graymauser/btcposbal2csv
* https://blockchair.com/dumps
* https://balances.crypto-nerdz.org/

## Find addresses
**Attention**: Do not use this software in a productive, non safe environment. A safe environment might be a dedicated computer with an air gap / disconnected network. A side-channel attack is possible and the software is optimized for performance and not constant-time. You may use a [paper wallet](https://en.bitcoin.it/wiki/Paper_wallet) for created vanity keys.

### Mixed modes
Find personal vanity addresses and check if addresses already exists in the lmdb can be used together.

### Key range
A key range can be defined (e.g. 64-bit) whereas the first (e.g. 192-bit (256-bit - 64-bit)) are zeroed. This can be used to creaty keys in a specific range to find keys in a known range (e.g. [Bitcoin Puzzle Transaction](https://privatekeys.pw/puzzles/bitcoin-puzzle-tx)).
This can be also used to proof that the software works.

### OpenCL
To increase the performance of the EC-key generation OpenCL can be used.
A common secret is transfered to the OpenCL device with a fixed grid size. Each OpenCL thread creates a different EC-Key because it add its thread-id to the secret. Therefore a range of EC-keys for a fixed grid size is created at once and will be transfered back to the main memory.
The CPU is now able to hash the x,y coordinate of the EC-key to create (Bitcoin/Altcoin) addresses.
The CPU doesn't spend most of its time for EC-key generation and can be used more efficient for hashing and database lookups.

The OpenCL mode has a Built-in self-test (BIST) to compare the OpenCL results with CPU based EC-Key generation. This allows an end user to verify it's OpenCL device is working properly.

#### Performance
The effective keys / s using uncompressed and compressed keys. OpenCL creates uncompressed keys only. A compressed key can be deduced easily from the uncompressed key.

GPU | privateKeyMaxNumBits | gridNumBits | effective keys / s
------------ | ------------- | ------------- | -------------
Nvidia RTX 2060 | 256 | 18 | 2160 k keys / s
Nvidia Quadro P2000 | 256 | 18 | 505 k keys /s
Nvidia Quadro P2000 | 64 | 18 | more than 1000 k keys /s (CPU was at its limit)
Nvidia Quadro M2000M | 256 | 16 | 205 k keys /s
Nvidia GTX 1050 Ti Mobile | 64 | 16 | more than 1000 k keys /s (CPU was at its limit)
Nvidia GTX 1050 Ti Mobile | 256 | 16 | 550 k keys /s

## Collision probability and security concerns
It's impossible to find collisions, isn't it? 
Please find the answear for vulnerability questions somewhere else:
* https://crypto.stackexchange.com/questions/33821/how-to-deal-with-collisions-in-bitcoin-addresses
* https://crypto.stackexchange.com/questions/47809/why-havent-any-sha-256-collisions-been-found-yet
* https://github.com/treyyoder/bitcoin-wallet-finder#results
* https://github.com/Frankenmint/PKGenerator_Checker#instructions
* https://github.com/Xefrok/BitBruteForce-Wallet#requeriments

## Similar projects
* The [LBC](https://lbc.cryptoguru.org/) is optimized to find keys for the [Bitcoin Puzzle Transaction](https://privatekeys.pw/puzzles/bitcoin-puzzle-tx). It require communication to a server, doesn't support altcoin and pattern matching.
* https://privatekeys.pw/scanner/bitcoin
* https://allprivatekeys.com/get-lucky
* https://allprivatekeys.com/vanity-address
* https://github.com/treyyoder/bitcoin-wallet-finder
* https://github.com/albertobsd/keyhunt
* https://github.com/mvrc42/bitp0wn
* https://github.com/JeanLucPons/BTCCollider
* https://github.com/JeanLucPons/VanitySearch
* https://github.com/JamieAcharya/Bitcoin-Private-Key-Finder
* https://github.com/mingfunwong/all-bitcoin-private-key
* https://github.com/Frankenmint/PKGenerator_Checker
* https://github.com/Henshall/BitcoinPrivateKeyHunter
* https://github.com/Xefrok/BitBruteForce-Wallet
* https://github.com/Isaacdelly/Plutus
* https://github.com/Noname400/Hunt-to-Mnemonic
* https://github.com/Py-Project/Bitcoin-wallet-cracker

### Deep learning private key prediction
An export of the full database can be used to predict private keys with deep learning. A funny idea: https://github.com/DRSZL/BitcoinTensorFlowPrivateKeyPrediction

## Known issues
If you have a laptop like HP ZBook G3/G4/G5 "hybrid graphics" mode is very slow because of the shared memory. Please select in the BIOS "discrete graphics".

## Future improvements
- Refactor the entire key generation infrastructure to support a key provider. This provider should be configurable to supply private keys from various sources, such as Random, Secrets File, Key Range, and others. All consumers should retrieve keys from this provider.

### KeyProvider
- Key generation within a specific key range. See #27
Wished from themaster:
```
"privateKeyStartHex" : "0000000000000000000000000000000000000000000000037e26d5b1f3afe216"
"privateKeyEndHex" : "0000000000000000000000000000000000000000000000037e26d5b1ffffffff"
```
Wished from Ulugbek:
```
// Search started from given address. Would be nice if it can save last position...
"sequentalSearch" : true,
"startAddress" : xxxxxxxx,

// Random search with batches, here 100000. I,e. some random number is found and after 100000 sequental addresses should be checked.
"searchAsBatches" : true,
"searchBatchQuantity" : 100000,


// Random search within Address Space, with batches, here 100000.
"searchAsBatches" : true,
"searchAddressStart" : xxxxxxx,
"searchAddressEnd" : xxxxxxxy,
"searchBatchQuantity" : 100000
```

- Incomplete Seed-Phrase as Private KeyProvider. Wished from @mirasu See #38
- Socket KeyProvider for independend KeyProvider via byte protocol
  - Ideas might be a screen recorder and use the visible screen downscaled as 256 bit input
- KeyProvider must get the grid size to increment properly on incremental based Producer
- ExecutableKeyProvider gets data from stdout

-----
## Legal
This software should not be configured and used to find (Bitcoin/Altcoin) address hash (RIPEMD-160) collisions and use (steal) credit from third-party (Bitcoin/Altcoin) addresses.
This mode might be allowed to recover lost private keys of your own public addresses only.

Another mostly legal use case is a check if the (Bitcoin/Altcoin) addresses hash (RIPEMD-160) is already in use to prevent yourself from a known hash (RIPEMD-160) collision and double use.

Some configurations are not allowed in some countries (definitely not complete):
* Germany: § 202c Vorbereiten des Ausspähens und Abfangens von Daten
* United States of America (USA): Computer Fraud and Abuse Act (CFAA)

## License

It is licensed under the Apache License, Version 2.0. See [LICENSE](LICENSE) for the full license text.
Some subprojects have a different license.



[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fbernardladenthin%2FBitcoinAddressFinder.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fbernardladenthin%2FBitcoinAddressFinder?ref=badge_large)