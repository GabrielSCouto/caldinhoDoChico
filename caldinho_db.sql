-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 29/11/2024 às 17:43
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `caldinho_db`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `itemcardapio`
--

CREATE TABLE `itemcardapio` (
  `idItem` int(10) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `descricao` text DEFAULT NULL,
  `preco` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `itemcardapio`
--

INSERT INTO `itemcardapio` (`idItem`, `nome`, `descricao`, `preco`) VALUES
(17, 'Banana frita com canela', 'Banana frita ao óleo com pitada de canela em pó', 15.00),
(18, 'Salada de frutas', 'maçã, bana, goiaba e manga picotados com mel e leite em pó', 13.00);

-- --------------------------------------------------------

--
-- Estrutura para tabela `mesa`
--

CREATE TABLE `mesa` (
  `numero` int(11) NOT NULL,
  `livre` enum('Ocupado','Livre') CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `mesa`
--

INSERT INTO `mesa` (`numero`, `livre`) VALUES
(1, 'Livre'),
(2, 'Livre'),
(3, 'Livre'),
(4, 'Livre'),
(5, 'Ocupado'),
(6, 'Livre'),
(7, 'Ocupado'),
(8, 'Ocupado'),
(9, 'Ocupado'),
(10, 'Livre');

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedidos`
--

CREATE TABLE `pedidos` (
  `idPedido` int(11) NOT NULL,
  `numeroMesa` int(11) NOT NULL,
  `idItem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pedidos`
--

INSERT INTO `pedidos` (`idPedido`, `numeroMesa`, `idItem`) VALUES
(2, 7, 17),
(3, 7, 18);

-- --------------------------------------------------------

--
-- Estrutura para tabela `relatoriopedido`
--

CREATE TABLE `relatoriopedido` (
  `idRelatorio` int(11) NOT NULL,
  `idPedido` int(11) DEFAULT NULL,
  `valorTotal` decimal(10,2) DEFAULT NULL,
  `dataRelatorio` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `itemcardapio`
--
ALTER TABLE `itemcardapio`
  ADD PRIMARY KEY (`idItem`);

--
-- Índices de tabela `mesa`
--
ALTER TABLE `mesa`
  ADD PRIMARY KEY (`numero`);

--
-- Índices de tabela `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`idPedido`),
  ADD KEY `numeroMesa` (`numeroMesa`),
  ADD KEY `idItem` (`idItem`);

--
-- Índices de tabela `relatoriopedido`
--
ALTER TABLE `relatoriopedido`
  ADD PRIMARY KEY (`idRelatorio`),
  ADD KEY `idPedido` (`idPedido`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `itemcardapio`
--
ALTER TABLE `itemcardapio`
  MODIFY `idItem` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de tabela `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `idPedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `relatoriopedido`
--
ALTER TABLE `relatoriopedido`
  MODIFY `idRelatorio` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`numeroMesa`) REFERENCES `mesa` (`numero`),
  ADD CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`idItem`) REFERENCES `itemcardapio` (`idItem`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
