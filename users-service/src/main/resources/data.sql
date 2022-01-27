

INSERT INTO `roles` (`id`, `name`) VALUES
(2, 'ROLE_ADMIN'),
(1, 'ROLE_USER');

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `email`, `enabled`, `last_name`, `name`, `password`, `username`, `attempts`) VALUES
(1, 'alexlondon07@gmail.com', b'1', 'Londoño', 'Alexander', '$2a$10$IYUIxbIsHZ82DejtFsNmvuvBXGRfzwxLMyV1ixuP0sAC9zX4LLX/W', 'alexlondon07', NULL),
(2, 'emi@gmail.com', b'1', 'Londoño', 'emi', '$2a$10$IYUIxbIsHZ82DejtFsNmvuvBXGRfzwxLMyV1ixuP0sAC9zX4LLX/W', 'emi', NULL),
(3, 'pepito@gmail.com', b'1', 'perez', 'pepito', '$2a$10$IYUIxbIsHZ82DejtFsNmvuvBXGRfzwxLMyV1ixuP0sAC9zX4LLX/W', 'pepito update', NULL);

--
-- Volcado de datos para la tabla `users_x_roles`
--

INSERT INTO `users_x_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 1),
(3, 2);
COMMIT;
