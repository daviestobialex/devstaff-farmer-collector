CREATE TABLE `Farms` (
  `id` int PRIMARY KEY,
  `name` varchar(100),
  `description` text,
  `latitude` bigdecimal,
  `longitude` bigdecimal,
  `created_at` datetime,
  `last_modified` datetime
);

CREATE TABLE `Plantations` (
  `id` int PRIMARY KEY,
  `farm_id` int,
  `created_at` datetime,
  `last_modified` datetime,
  `start_plantation` date,
  `expected_harvest_date` date,
  `season` ENUM ('Autumn', 'Spring', 'Winter', 'Summer'),
  `cropType` varchar(150),
  `planting_area_in_acres` double,
  `expected_quantity_in_tons` double
);

CREATE TABLE `Harvests` (
  `id` int PRIMARY KEY,
  `farm_id` int,
  `plantation_id` int,
  `created_at` datetime,
  `last_modified` datetime,
  `season` ENUM ('Autumn', 'Spring', 'Winter', 'Summer'),
  `cropType` varchar(150),
  `actual_quantity_in_tons` double
);

ALTER TABLE `Farms` ADD FOREIGN KEY (`id`) REFERENCES `Plantations` (`farm_id`);

ALTER TABLE `Farms` ADD FOREIGN KEY (`id`) REFERENCES `Harvests` (`farm_id`);

ALTER TABLE `Plantations` ADD FOREIGN KEY (`id`) REFERENCES `Harvests` (`plantation_id`);
