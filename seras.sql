-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 24/01/2025 às 03:20
-- Versão do servidor: 8.0.30
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `seras`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `id` int NOT NULL,
  `nome` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `cpf` varchar(14) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `telefone` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `endereco` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`id`, `nome`, `cpf`, `email`, `telefone`, `endereco`) VALUES
(1, 'João Silva', '123.456.789-00', 'joao@gmail.com', '11987654321', 'Rua A, 123'),
(2, 'Maria Oliveira', '987.654.321-00', 'maria@gmail.com', '11998765432', 'Rua B, 456'),
(3, 'Carlos Santos', '456.789.123-00', 'carlos@gmail.com', '11912345678', 'Rua C, 789'),
(5, 'a', '111.111.111-12', 'a', '2', 'a'),
(7, 'asd', '111.111.111-11', 'asd', 'as', 'asd'),
(8, 'ko', '000.000.000-03', 'ko', '123', 'ko'),
(9, 'João da Silva', '12345678900', 'joao.silva@example.com', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `emprestimo`
--

CREATE TABLE `emprestimo` (
  `id` int NOT NULL,
  `proposta_id` int NOT NULL,
  `valor_aprovado` double NOT NULL,
  `numero_parcelas` int NOT NULL,
  `saldo_devedor` double NOT NULL,
  `data_inicio` date NOT NULL,
  `taxa_juros_mensal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `emprestimo`
--

INSERT INTO `emprestimo` (`id`, `proposta_id`, `valor_aprovado`, `numero_parcelas`, `saldo_devedor`, `data_inicio`, `taxa_juros_mensal`) VALUES
(1, 2, 123, 3, 123, '2025-01-07', 3.34),
(2, 4, 3123, 3, 3123, '2025-01-07', 10),
(4, 10, 123132, 123, 123132, '2025-01-08', 2),
(7, 16, 111, 111, 111, '2025-01-15', 111),
(8, 17, 33, 33, 33, '2025-01-15', 7);

-- --------------------------------------------------------

--
-- Estrutura para tabela `log_acessos`
--

CREATE TABLE `log_acessos` (
  `id` int NOT NULL,
  `ip` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `data_hora` datetime NOT NULL,
  `pagina` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `id_usuario` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `log_acessos`
--

INSERT INTO `log_acessos` (`id`, `ip`, `data_hora`, `pagina`, `id_usuario`) VALUES
(1, '0:0:0:0:0:0:0:1', '2025-01-16 12:04:34', '/seras/index.jsp', NULL),
(2, '0:0:0:0:0:0:0:1', '2025-01-16 12:04:36', '/seras/buscar_emprestimo.jsp', NULL),
(3, '0:0:0:0:0:0:0:1', '2025-01-16 12:04:38', '/seras/buscar_propostas.jsp', NULL),
(4, '0:0:0:0:0:0:0:1', '2025-01-16 12:04:39', '/seras/login.jsp', NULL),
(5, '0:0:0:0:0:0:0:1', '2025-01-16 12:04:40', '/seras/LoginServlet', NULL),
(6, '0:0:0:0:0:0:0:1', '2025-01-16 12:04:41', '/seras/index.jsp', 1),
(7, '0:0:0:0:0:0:0:1', '2025-01-16 12:04:41', '/seras/buscar_propostas.jsp', 1),
(8, '0:0:0:0:0:0:0:1', '2025-01-16 12:04:42', '/seras/atualizar_proposta.jsp', 1),
(9, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:08', '/seras/index.jsp', NULL),
(10, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:08', '/seras/index.jsp', NULL),
(11, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:16', '/seras/TemaServlet', NULL),
(12, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:16', '/seras/TemaServlet', NULL),
(13, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:16', '/seras/login.jsp', NULL),
(14, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:16', '/seras/login.jsp', NULL),
(15, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:20', '/seras/LoginServlet', NULL),
(16, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:20', '/seras/LoginServlet', NULL),
(17, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:20', '/seras/index.jsp', 1),
(18, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:20', '/seras/index.jsp', 1),
(19, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:25', '/seras/cadastrar_cliente.jsp', 1),
(20, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:25', '/seras/cadastrar_cliente.jsp', 1),
(21, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:40', '/seras/ClienteController', 1),
(22, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:40', '/seras/ClienteController', 1),
(23, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:40', '/seras/sucesso.jsp', 1),
(24, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:40', '/seras/sucesso.jsp', 1),
(25, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:44', '/seras/index.jsp', 1),
(26, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:44', '/seras/index.jsp', 1),
(27, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:54', '/seras/cadastrar_proposta.jsp', 1),
(28, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:54', '/seras/cadastrar_proposta.jsp', 1),
(29, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:59', '/seras/PropostaAddServlet', 1),
(30, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:59', '/seras/PropostaAddServlet', 1),
(31, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:59', '/seras/sucesso.jsp', 1),
(32, '0:0:0:0:0:0:0:1', '2025-01-16 13:49:59', '/seras/sucesso.jsp', 1),
(33, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:01', '/seras/index.jsp', 1),
(34, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:01', '/seras/index.jsp', 1),
(35, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:08', '/seras/buscar_propostas.jsp', 1),
(36, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:08', '/seras/buscar_propostas.jsp', 1),
(37, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:12', '/seras/BuscarPropostasServlet', 1),
(38, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:12', '/seras/BuscarPropostasServlet', 1),
(39, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:19', '/seras/AprovarOuNegarPropostaServlet', 1),
(40, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:19', '/seras/AprovarOuNegarPropostaServlet', 1),
(41, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:19', '/seras/buscar_propostas.jsp', 1),
(42, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:19', '/seras/buscar_propostas.jsp', 1),
(43, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:21', '/seras/index.jsp', 1),
(44, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:21', '/seras/index.jsp', 1),
(45, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:22', '/seras/buscar_emprestimo.jsp', 1),
(46, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:22', '/seras/buscar_emprestimo.jsp', 1),
(47, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:25', '/seras/BuscarEmprestimoServlet', 1),
(48, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:25', '/seras/BuscarEmprestimoServlet', 1),
(49, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:30', '/seras/RemoverEmprestimoServlet', 1),
(50, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:30', '/seras/RemoverEmprestimoServlet', 1),
(51, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:30', '/seras/sucesso.jsp', 1),
(52, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:30', '/seras/sucesso.jsp', 1),
(53, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:32', '/seras/index.jsp', 1),
(54, '0:0:0:0:0:0:0:1', '2025-01-16 13:50:32', '/seras/index.jsp', 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `proposta_emprestimo`
--

CREATE TABLE `proposta_emprestimo` (
  `id` int NOT NULL,
  `cliente_id` int NOT NULL,
  `valor_solicitado` decimal(10,2) NOT NULL,
  `numero_parcelas` int NOT NULL,
  `data_solicitacao` date NOT NULL,
  `status` varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'PENDENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `proposta_emprestimo`
--

INSERT INTO `proposta_emprestimo` (`id`, `cliente_id`, `valor_solicitado`, `numero_parcelas`, `data_solicitacao`, `status`) VALUES
(1, 1, 123.00, 3, '2025-01-07', 'negado'),
(2, 1, 123.00, 3, '2025-01-07', 'aprovado'),
(3, 1, 123.00, 2, '2025-01-07', 'NEGADO'),
(4, 2, 3123.00, 3, '2025-01-07', 'APROVADO'),
(5, 2, 123323.00, 34, '2025-01-08', 'PENDENTE'),
(6, 3, 12334.00, 55444, '2025-01-08', 'PENDENTE'),
(10, 5, 123132.00, 123, '2025-01-08', 'APROVADO'),
(11, 5, 123.00, 123, '2025-01-08', 'NEGADO'),
(15, 6, 123.00, 123, '2025-01-15', 'PENDENTE'),
(16, 7, 222.00, 123, '2025-01-15', 'APROVADO'),
(17, 7, 33.00, 33, '2025-01-15', 'APROVADO'),
(18, 8, 123.00, 123, '2025-01-16', 'APROVADO');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`id`, `username`, `password`) VALUES
(1, 'admin', '123');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cpf` (`cpf`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Índices de tabela `emprestimo`
--
ALTER TABLE `emprestimo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_emprestimo_proposta` (`proposta_id`);

--
-- Índices de tabela `log_acessos`
--
ALTER TABLE `log_acessos`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `proposta_emprestimo`
--
ALTER TABLE `proposta_emprestimo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_proposta_cliente` (`cliente_id`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `emprestimo`
--
ALTER TABLE `emprestimo`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `log_acessos`
--
ALTER TABLE `log_acessos`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT de tabela `proposta_emprestimo`
--
ALTER TABLE `proposta_emprestimo`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
