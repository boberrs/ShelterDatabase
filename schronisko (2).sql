-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 07 Sty 2017, 15:15
-- Wersja serwera: 10.1.16-MariaDB
-- Wersja PHP: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `schronisko`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `adopcja`
--

CREATE TABLE `adopcja` (
  `ID` int(10) NOT NULL,
  `Zwierzę` varchar(15) NOT NULL,
  `OsobaAdoptująca` int(10) NOT NULL,
  `PracownikWydający` int(11) NOT NULL,
  `DataAdopcji` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kwarantanna`
--

CREATE TABLE `kwarantanna` (
  `ID` int(10) NOT NULL,
  `Długość` int(3) NOT NULL,
  `Wiek` date NOT NULL,
  `Płeć` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `osoba`
--

CREATE TABLE `osoba` (
  `ID` int(10) NOT NULL,
  `PESEL` varchar(11) DEFAULT NULL,
  `Imię` varchar(20) DEFAULT NULL,
  `Nazwisko` varchar(20) DEFAULT NULL,
  `Adres` varchar(50) DEFAULT NULL,
  `NumerTelefonu` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `osoba`
--

INSERT INTO `osoba` (`ID`, `PESEL`, `Imię`, `Nazwisko`, `Adres`, `NumerTelefonu`) VALUES
(1, '96011208945', 'Karolina', 'Petunia', 'ul. Olszewskiego 154, Trzebnica', '691222225'),
(2, '94110309838', 'Janusz', 'Kwiat', 'ul. Przyrodnia 12, Warszawa', '668901234'),
(3, '92121209222', 'Anita', 'Krawczyk', 'ul. Zachodnia 2, Wroclaw', '679012345'),
(4, '87030390389', 'Michal', 'Rodzynek', 'ul. Kwiatowa 34, Wroclaw', '567890567'),
(5, '56090928945', 'Karol', 'Jakubiak', 'ul. Pomorska 33, Wroclaw', '456789045'),
(6, '74080934567', 'Ewelina', 'Myszkowska', 'ul. Niemiecka 14, Wroclaw', '567456908'),
(7, '87022189678', 'Antoni', 'Drzwi', 'ul. Sowia 59', '678906789');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `ID` int(10) NOT NULL,
  `PESEL` varchar(11) NOT NULL,
  `Imię` varchar(20) NOT NULL,
  `Nazwisko` varchar(20) NOT NULL,
  `DataUrodzenia` date NOT NULL,
  `Adres` varchar(50) NOT NULL,
  `Wykształcenie` varchar(10) NOT NULL,
  `Stanowisko` varchar(15) NOT NULL,
  `Płaca` int(11) NOT NULL,
  `DataZatrudnienia` date NOT NULL,
  `RodzajUmowy` varchar(10) NOT NULL,
  `Etat` varchar(10) NOT NULL,
  `DataOstatniegoBadania` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `pracownik`
--

INSERT INTO `pracownik` (`ID`, `PESEL`, `Imię`, `Nazwisko`, `DataUrodzenia`, `Adres`, `Wykształcenie`, `Stanowisko`, `Płaca`, `DataZatrudnienia`, `RodzajUmowy`, `Etat`, `DataOstatniegoBadania`) VALUES
(1, '90011298756', 'Natalia', 'Rozdymka', '1990-01-12', 'ul. Karkonoska 12, Wroclaw', 'podstawowe', 'ksiegowa', 1900, '2017-01-01', 'o prace', 'caly', '2016-12-21'),
(2, '96011709872', 'Karol', 'Jackowiak', '1996-01-17', 'ul. Slabiutka 13, Opole', 'wyzsze', 'sprzatacz', 1800, '2015-01-04', 'zlecenie', 'pol', '2016-12-20'),
(3, '67040589073', 'Marcin', 'Otwarty', '1967-04-05', 'ul. Polna 90, Wroclaw', 'srednie', 'Dyrektor', 6500, '2010-09-18', 'o prace', 'caly', '2017-01-01');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przyjęcia`
--

CREATE TABLE `przyjęcia` (
  `ID` int(10) NOT NULL,
  `Zwierzę` varchar(15) NOT NULL,
  `OsobaOddająca` int(10) NOT NULL,
  `DataPrzyjęcia` date NOT NULL,
  `MiejsceZnalezienia` varchar(50) NOT NULL,
  `Uwagi` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `przyjęcia`
--

INSERT INTO `przyjęcia` (`ID`, `Zwierzę`, `OsobaOddająca`, `DataPrzyjęcia`, `MiejsceZnalezienia`, `Uwagi`) VALUES
(1, '56HJ78KJH789DF4', 1, '2017-01-03', 'ul. Strzegomska', 'zwierze bardzo wystraszone'),
(2, '9GJTK4GJTK69SN4', 2, '2017-01-01', 'przy Odrze', 'brak'),
(3, '9GJTK4KRH47SK2H', 4, '2016-12-12', 'ul. Obornicka', 'zwierze agresywne'),
(4, '67GJSL305GBSJ34', 3, '2017-01-04', 'Dworzec Nadodrze', 'brak'),
(5, '56HJ78KJH789JK4', 2, '2017-01-03', 'ul. Dworcowa', 'brak'),
(6, '67GJSL390GBSJ34', 3, '2016-11-14', 'przy Odrze', 'zwierze wystraszone'),
(7, '475JDL10DGBFSK4', 1, '2016-09-12', 'ul. Slazowa', 'wychudzony'),
(8, '475JDL10DNBJSK4', 1, '2017-01-02', 'ul. Opolska', 'brak'),
(9, '1234FDSA1243GHJ', 1, '2012-02-02', 'Partynice', 'brak'),
(10, '1234FDSA1234GHJ', 3, '2011-01-11', 'Osiedle Truskawkowe', 'brak'),
(11, '238679FDVGKERT5', 4, '2010-11-11', 'Opole', ''),
(12, '238679FHJGKERT5', 3, '2009-09-09', 'ul. Polna', ''),
(13, '67895678GHJG66G', 2, '2016-09-07', 'ul. Sztabowa', 'zwierze kuleje'),
(14, '67895678GHJK56G', 1, '2015-07-07', '', 'zwierze oddane do adopcji'),
(15, '234565432SDFG56', 2, '2014-11-15', '', 'zwierze oddane do adopcji'),
(16, 'CBFJ69SL57CHDK3', 3, '2010-03-17', '', 'zwierze oddane do adopcji'),
(17, '234565432SDFG56', 5, '2015-01-14', 'przy drodze', 'zwierze wychudzone'),
(18, '234565433SDFG56', 5, '2013-10-29', 'ul. Przydrozna', 'zwierze wystraszone'),
(19, 'ACBF4550GBDF345', 6, '2016-12-26', 'pod choinka', 'zwierze oddane do adopcji'),
(20, 'ACBF4550GBDF456', 6, '2012-02-14', 'wygrana w konkursie', 'zwierze oddane do adopcji'),
(21, 'AKFL58DHBKASD02', 4, '2009-09-15', 'ul. Przejsciowa', 'zwierze ma obnizona odpornosc'),
(22, 'AKFL58DHBKFJ302', 6, '2008-08-08', 'przybleda', 'zwierze oddane do adopcji'),
(23, 'AKFL58DHBKFJ302', 6, '2015-10-15', 'ul. Niepolna', ''),
(24, 'AS34BN56NM34JK6', 1, '2017-01-04', 'przybleda', 'zwierze oddane do adopcji'),
(25, 'ASDCVB234SDF567', 6, '2017-01-03', '', 'smierc wlasciciela'),
(26, 'ASDCVB234SDF567', 3, '2016-07-04', 'w rowie', 'zwierze wychudzone'),
(27, 'ASDFGHJK2333678', 5, '2007-07-06', 'ul. Trzebnicka', 'zwierze pogryzlo wolontariuszy'),
(28, 'ASDFGHJK2345678', 2, '2001-11-11', 'ul. Jedenasta', ''),
(29, 'CBFJ69SL57CHDK3', 6, '2016-12-01', 'ul. Znajoma', 'zwierze wystraszone'),
(30, 'CV34BN56NM34JK6', 6, '2010-09-01', 'ul. Przytomna', 'zwierze ma chore zeby'),
(31, 'CV45YUO8NM78SD3', 5, '2017-01-05', 'ul. Przyrodnia', 'brak'),
(32, 'CV45YUO8NM78SD3', 5, '2017-01-01', 'ul. Targowa', 'zwierze ma obroze i smycz'),
(33, 'GJ7L30CNBJKSL45', 6, '2011-05-05', 'ul. Sztabowa', ''),
(34, 'GJ7L30CNFGHSL45', 2, '2008-09-26', 'Opatowice', 'zwierze agresywne'),
(35, 'GJHKBLS568409S5', 5, '2012-09-30', '', 'zwierze oddane do adopcji');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wizytalekarska`
--

CREATE TABLE `wizytalekarska` (
  `ID` int(10) NOT NULL,
  `Lekarz` int(10) NOT NULL,
  `Zwierzę` varchar(15) NOT NULL,
  `RodzajZabiegu` varchar(20) NOT NULL,
  `Szczegóły` varchar(150) NOT NULL,
  `Uwagi` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zwierzę`
--

CREATE TABLE `zwierzę` (
  `Czip` varchar(15) NOT NULL,
  `Typ` varchar(40) NOT NULL,
  `Rasa` varchar(30) NOT NULL,
  `Maść` varchar(30) NOT NULL,
  `Płeć` varchar(2) NOT NULL,
  `Wiek` date NOT NULL,
  `NrKlatki` varchar(5) NOT NULL,
  `Uwagi` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `zwierzę`
--

INSERT INTO `zwierzę` (`Czip`, `Typ`, `Rasa`, `Maść`, `Płeć`, `Wiek`, `NrKlatki`, `Uwagi`) VALUES
('1234FDSA1234GHJ', 'Kot', 'Europejski', 'Rudy prazkowany', 'KS', '2006-06-06', '4', 'brak'),
('1234FDSA1243GHJ', 'Kot', 'Europejski', 'Rudy prazkowany', 'KS', '2016-06-06', '4', 'brak'),
('234565432SDFG56', 'Papuga', 'Falista', 'Niebieska', 'KN', '2016-11-11', '5', 'brak'),
('234565433SDFG56', 'Kot', 'Europejski', 'Rudy', 'MN', '2016-11-11', '4', 'brak'),
('238679FDVGKERT5', 'Pies', 'Mieszaniec', 'Rudy', 'MS', '2012-05-12', '3', 'brak'),
('238679FHJGKERT5', 'Pies', 'Mieszaniec', 'Brazowy', 'MS', '2011-05-02', '3', 'brak'),
('475JDL10DGBFSK4', 'Pies', 'Bullterier', 'Bialy', 'MS', '2011-12-21', '2', 'boi sie dzieci'),
('475JDL10DNBJSK4', 'Pies', 'Yorkshire Terier', 'Brazowo-czarny', 'MS', '2012-12-20', '2', 'brak'),
('56HJ78KJH789DF4', 'Pies', 'Bullterier', 'Czarny', 'KN', '2009-08-09', '7', 'agresywna'),
('56HJ78KJH789JK4', 'Pies', 'Bullterier', 'Czarny', 'KN', '2011-08-28', '7', 'gryzie'),
('67895678GHJG66G', 'Pies', 'Golden Retriever', 'Bezowy', 'MS', '2009-01-29', '3', 'boi sie kotow'),
('67895678GHJK56G', 'Pies', 'Golden Retriever', 'Bezowy', 'MS', '2007-11-19', '3', 'boi sie dzieci'),
('67GJSL305GBSJ34', 'Krowa', 'Jersey', 'Bezowa', 'KS', '2008-11-25', '10', 'brak'),
('67GJSL390GBSJ34', 'Krowa', 'Jersey', 'Bezowa', 'KN', '2007-10-25', '10', 'nieufna'),
('9GJTK4GJTK69SN4', 'Pies', 'Sznaucer Miniaturowy', 'Czarny', 'KS', '2013-02-03', '2', 'brak'),
('9GJTK4KRH47SK2H', 'Pies', 'Sznaucer Olbrzymi', 'Czarny', 'KS', '2015-01-02', '2', 'brak'),
('ACBF4550GBDF345', 'Kot', 'Europejska', 'Czarny', 'KN', '2016-05-10', '1', 'brak'),
('ACBF4550GBDF456', 'Kot', 'Europejska', 'Bialy', 'KN', '2016-06-10', '1', 'brak'),
('AKFL58DHBKASD02', 'Kot', 'Europejski', 'Trikolor', 'KS', '2008-11-18', '1', 'brak'),
('AKFL58DHBKFJ302', 'Kot', 'Europejski', 'Szary prazkowany', 'MN', '2005-01-28', '1', 'brak'),
('AS34BN56NM34JK6', 'Kot', 'Europejski', 'Bezowy', 'MS', '2009-08-27', '7', 'lubi pic oranzade'),
('ASDCVB234SDF567', 'Kot', 'Perski', 'Czarny', 'KS', '2015-05-05', '1', 'nie lubi kaszy'),
('ASDFGHJK2333678', 'Pies', 'Owczarek Niemiecki', 'Brazowo czarny', 'MS', '2015-01-13', '2', 'agresywny'),
('ASDFGHJK2345678', 'Pies', 'Owczarek Niemiecki', 'Bialo-czarny', 'MS', '2015-01-13', '2', 'agresywny'),
('CBFJ69SL57CHDK3', 'Kot', 'Balijski', 'Bialo-srebrny', 'MN', '2010-09-09', '1', 'brak'),
('CV34BN56NM34JK6', 'Kot', 'Birmanski', 'Bezowo-czarny', 'MS', '2008-08-17', '1', 'nie je suchej karmy'),
('CV45BN78NM78SD3', 'Pies', 'Bernardyn', 'Trikolor', 'MN', '2010-12-12', '2', 'brak'),
('CV45YUO8NM78SD3', 'Pies', 'Mieszaniec', 'Popielaty', 'MN', '2015-12-12', '2', 'brak'),
('GJ7L30CNBJKSL45', 'Pies', 'Beagle', 'Trikolor', 'KS', '2013-05-01', '3', 'brak'),
('GJ7L30CNFGHSL45', 'Pies', 'Dalmatynczyk', 'Bialo-czarny', 'KS', '2012-05-01', '3', 'brak'),
('GJHKBLS568409S5', 'Pies', 'Mieszaniec', 'Rudo-czarny', 'MN', '2012-07-19', '6', 'boi sie ogorkow'),
('GJHKBLSNV8409S5', 'Pies', 'Mieszaniec', 'Szaro-czarny', 'MN', '2012-09-09', '6', 'boi sie ziemniakow'),
('HJKLFG5678HJK67', 'Koza', 'Alpejska', 'Biala', 'KN', '1995-01-02', '10', 'zje wszystko. nawet ludzi'),
('HJKLFG5678HPK67', 'Koza', 'Mleczna', 'Szara', 'KN', '2005-01-08', '10', 'agresywna'),
('NGJDKEO58GJ60S2', 'Papuga', 'Ara', 'Szara', 'KN', '2013-02-01', '5', 'nie umie latac'),
('VNGK710SJ123JT', 'Chomik', 'Rosyjski', 'szary', 'MN', '2015-10-13', '6', 'brak'),
('VNGK710SJFHGJT', 'Mysz', 'Polna', 'brazowa', 'KN', '2016-04-12', '6', 'dzika'),
('ZXCCVB234SDF567', 'Kot', 'Perski', 'Bialy', 'KS', '2015-05-05', '1', 'nie lubi ziemniakow');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `adopcja`
--
ALTER TABLE `adopcja`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Zwierzę` (`Zwierzę`),
  ADD KEY `Osoba Adoptująca` (`OsobaAdoptująca`),
  ADD KEY `Pracownik Wydający` (`PracownikWydający`);

--
-- Indexes for table `kwarantanna`
--
ALTER TABLE `kwarantanna`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `osoba`
--
ALTER TABLE `osoba`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UNIQUE` (`PESEL`);

--
-- Indexes for table `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `DISTINCT` (`PESEL`);

--
-- Indexes for table `przyjęcia`
--
ALTER TABLE `przyjęcia`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Zwierzę` (`Zwierzę`),
  ADD KEY `Osoba oddająca` (`OsobaOddająca`);

--
-- Indexes for table `wizytalekarska`
--
ALTER TABLE `wizytalekarska`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Lekarz` (`Lekarz`),
  ADD KEY `Zwierzę` (`Zwierzę`);

--
-- Indexes for table `zwierzę`
--
ALTER TABLE `zwierzę`
  ADD PRIMARY KEY (`Czip`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `adopcja`
--
ALTER TABLE `adopcja`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `osoba`
--
ALTER TABLE `osoba`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT dla tabeli `przyjęcia`
--
ALTER TABLE `przyjęcia`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT dla tabeli `wizytalekarska`
--
ALTER TABLE `wizytalekarska`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
