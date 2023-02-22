-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 18 fév. 2023 à 10:30
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonnement`
--

CREATE TABLE `abonnement` (
  `id` int(11) NOT NULL,
  `date_achat` date NOT NULL,
  `id_ty` int(11) NOT NULL,
  `id_c` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `abonnement`
--

INSERT INTO `abonnement` (`id`, `date_achat`, `id_ty`, `id_c`) VALUES
(1, '2024-01-01', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `id_avis` int(11) NOT NULL,
  `note` varchar(30) NOT NULL,
  `description` varchar(100) NOT NULL,
  `id_c` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `avis`
--

INSERT INTO `avis` (`id_avis`, `note`, `description`, `id_c`) VALUES
(9, 'moyenne', 'tout est bien passé', 1);

-- --------------------------------------------------------

--
-- Structure de la table `carte_fidelite`
--

CREATE TABLE `carte_fidelite` (
  `numero` int(11) NOT NULL,
  `nbr_point` int(11) NOT NULL,
  `id_u` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `carte_fidelite`
--

INSERT INTO `carte_fidelite` (`numero`, `nbr_point`, `id_u`) VALUES
(3, 100, 2),
(4, 100, 1);

-- --------------------------------------------------------

--
-- Structure de la table `categorievehicule`
--

CREATE TABLE `categorievehicule` (
  `id_cat` int(11) NOT NULL,
  `nom_cat` varchar(50) NOT NULL,
  `id_vh` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `categorievehicule`
--

INSERT INTO `categorievehicule` (`id_cat`, `nom_cat`, `id_vh`) VALUES
(11, '24', 5);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id_f` int(11) NOT NULL,
  `id_c` int(11) NOT NULL,
  `nbr_heure` float NOT NULL,
  `type` varchar(20) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id_f`, `id_c`, `nbr_heure`, `type`, `date`) VALUES
(1, 2, 2, 'ali', '2044-01-01'),
(2, 2, 2, 'hey', '2044-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `maintenance`
--

CREATE TABLE `maintenance` (
  `id_m` int(11) NOT NULL,
  `id_v` int(11) NOT NULL,
  `cout` float NOT NULL,
  `duree` float NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_rec` int(11) NOT NULL,
  `type` varchar(30) NOT NULL,
  `description` varchar(100) NOT NULL,
  `id_c` int(11) NOT NULL,
  `etat` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_rec`, `type`, `description`, `id_c`, `etat`) VALUES
(7, 'service', 'retard', 2, 'traité'),
(19, 'bcde', 'panne', 1, 'non traité');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_r` int(11) NOT NULL,
  `id_c` int(11) NOT NULL,
  `id_v` int(11) NOT NULL,
  `cin` int(11) NOT NULL,
  `num_phone` int(11) NOT NULL,
  `etat` varchar(20) NOT NULL,
  `conditionA` int(1) NOT NULL,
  `date_res` date NOT NULL,
  `prix` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `type_abonnement`
--

CREATE TABLE `type_abonnement` (
  `id` int(11) NOT NULL,
  `periode` float NOT NULL,
  `description` varchar(100) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `offre` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `type_abonnement`
--

INSERT INTO `type_abonnement` (`id`, `periode`, `description`, `nom`, `offre`) VALUES
(1, 12, 'heyy', 'type1', 12),
(2, 12, '!!!!', 'type1', 12);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `date_n` date NOT NULL,
  `genre` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mdp` varchar(10) NOT NULL,
  `type` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `date_n`, `genre`, `email`, `mdp`, `type`) VALUES
(1, 'bm', 'zeineb', '2001-06-26', 'femme', 'zeinebbm@gmail.com', 'zz', 'A'),
(2, 'mn', 'imen', '2001-06-26', 'femme', 'imenmn@gmail.com', 'ii', 'A');

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

CREATE TABLE `vehicule` (
  `id_vehicule` int(11) NOT NULL,
  `cat_vehicule` varchar(50) NOT NULL,
  `poid_sup` int(11) NOT NULL,
  `vitesse` int(11) NOT NULL,
  `nbr_pas` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`id_vehicule`, `cat_vehicule`, `poid_sup`, `vitesse`, `nbr_pas`, `status`) VALUES
(4, '6', 13, 2, 5, 1),
(5, '6', 14, 2, 5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `vol`
--

CREATE TABLE `vol` (
  `id_v` int(11) NOT NULL,
  `id_mt` int(11) NOT NULL,
  `date` date NOT NULL,
  `destination` varchar(20) NOT NULL,
  `prix` float NOT NULL,
  `etat` varchar(20) NOT NULL,
  `nbr_place` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_ty_fk` (`id_ty`),
  ADD KEY `a_fk` (`id_c`);

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id_avis`),
  ADD KEY `avis_fk` (`id_c`);

--
-- Index pour la table `carte_fidelite`
--
ALTER TABLE `carte_fidelite`
  ADD PRIMARY KEY (`numero`),
  ADD KEY `idc_fk` (`id_u`);

--
-- Index pour la table `categorievehicule`
--
ALTER TABLE `categorievehicule`
  ADD PRIMARY KEY (`id_cat`),
  ADD KEY `cv_fk` (`id_vh`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id_f`),
  ADD KEY `id_c_fk` (`id_c`);

--
-- Index pour la table `maintenance`
--
ALTER TABLE `maintenance`
  ADD PRIMARY KEY (`id_m`),
  ADD KEY `id_v_fk` (`id_v`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_rec`),
  ADD KEY `rec_fk` (`id_c`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_r`) USING BTREE,
  ADD KEY `idv_fk` (`id_v`),
  ADD KEY `client_fk` (`id_c`);

--
-- Index pour la table `type_abonnement`
--
ALTER TABLE `type_abonnement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD PRIMARY KEY (`id_vehicule`);

--
-- Index pour la table `vol`
--
ALTER TABLE `vol`
  ADD PRIMARY KEY (`id_v`),
  ADD KEY `vol_fk` (`id_mt`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `abonnement`
--
ALTER TABLE `abonnement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `carte_fidelite`
--
ALTER TABLE `carte_fidelite`
  MODIFY `numero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `categorievehicule`
--
ALTER TABLE `categorievehicule`
  MODIFY `id_cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id_f` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `maintenance`
--
ALTER TABLE `maintenance`
  MODIFY `id_m` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_r` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `type_abonnement`
--
ALTER TABLE `type_abonnement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `vehicule`
--
ALTER TABLE `vehicule`
  MODIFY `id_vehicule` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `vol`
--
ALTER TABLE `vol`
  MODIFY `id_v` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD CONSTRAINT `a_fk` FOREIGN KEY (`id_c`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `id_ty_fk` FOREIGN KEY (`id_ty`) REFERENCES `type_abonnement` (`id`);

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `avis_fk` FOREIGN KEY (`id_c`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `carte_fidelite`
--
ALTER TABLE `carte_fidelite`
  ADD CONSTRAINT `idc_fk` FOREIGN KEY (`id_u`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `categorievehicule`
--
ALTER TABLE `categorievehicule`
  ADD CONSTRAINT `cv_fk` FOREIGN KEY (`id_vh`) REFERENCES `vehicule` (`id_vehicule`);

--
-- Contraintes pour la table `formation`
--
ALTER TABLE `formation`
  ADD CONSTRAINT `id_c_fk` FOREIGN KEY (`id_c`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `maintenance`
--
ALTER TABLE `maintenance`
  ADD CONSTRAINT `id_v_fk` FOREIGN KEY (`id_v`) REFERENCES `vol` (`id_v`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `rec_fk` FOREIGN KEY (`id_c`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `client_fk` FOREIGN KEY (`id_c`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `idv_fk` FOREIGN KEY (`id_v`) REFERENCES `vol` (`id_v`);

--
-- Contraintes pour la table `vol`
--
ALTER TABLE `vol`
  ADD CONSTRAINT `vol_fk` FOREIGN KEY (`id_mt`) REFERENCES `vehicule` (`id_vehicule`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
