-- DROP TABLE options;
CREATE TABLE options (
    symbol varchar(255) NOT NULL,
    strike_price FLOAT,
    time_to_maturity INT NOT NULL,
    volatility FLOAT,
    PRIMARY KEY (symbol, time_to_maturity));
INSERT INTO options (symbol, strike_price, time_to_maturity, volatility) VALUES ('TSLA', 110.5, 1, 0.3);
INSERT INTO options (symbol, strike_price, time_to_maturity, volatility) VALUES ('TSLA', 123.5, 2, 0.3);
INSERT INTO options (symbol, strike_price, time_to_maturity, volatility) VALUES ('TSLA', 131.0, 3, 0.3);
INSERT INTO options (symbol, strike_price, time_to_maturity, volatility) VALUES ('AAPL', 52.0, 1, 0.2);
INSERT INTO options (symbol, strike_price, time_to_maturity, volatility) VALUES ('AAPL', 53.5, 2, 0.2);
INSERT INTO options (symbol, strike_price, time_to_maturity, volatility) VALUES ('AAPL', 55.5, 3, 0.2);