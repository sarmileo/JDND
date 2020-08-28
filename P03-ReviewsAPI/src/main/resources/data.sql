-- products
INSERT INTO product (productId, productName) VALUES (1, 'book');
INSERT INTO product (productId, productName) VALUES (2, 'computer');
INSERT INTO product (productId, productName) VALUES (3, 'bike');
INSERT INTO product (productId, productName) VALUES (4, 'shoes');

-- review
INSERT INTO review (id, content, title, productId)
VALUES (1, 'This book is great and interesting', 'Great book', 1);
INSERT INTO review (id, content, title, productId)
VALUES (2, 'This computer is great and fast', 'Great pc', 2);
INSERT INTO review (id, content, title, productId)
VALUES (3, 'This bike is great and has a nice color', 'Great bike', 3);
INSERT INTO review (id, content, title, productId)
VALUES (4, 'This shoes are great and comfortable', 'Great shoes', 4);

-- comment
INSERT INTO comment (content, reviewId)
VALUES ('YES This book is great', 1);
INSERT INTO comment (content, reviewId)
VALUES ('YES This computer is fast', 2);
INSERT INTO comment (content, reviewId)
VALUES ('NO This bike is not great', 3);
INSERT INTO comment (content, reviewId)
VALUES ('YES This shoes are great', 4);