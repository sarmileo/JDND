-- products
INSERT INTO product (id, name) VALUES (1, 'book');
INSERT INTO product (id, name) VALUES (2, 'computer');
INSERT INTO product (id, name) VALUES (3, 'bike');
INSERT INTO product (id, name) VALUES (4, 'shoes');

-- review
INSERT INTO review (id, content, title, product_id)
VALUES (1, 'This book is great and interesting', 'Great book', 1);
INSERT INTO review (id, content, title, product_id)
VALUES (2, 'This computer is great and fast', 'Great pc', 2);
INSERT INTO review (id, content, title, product_id)
VALUES (3, 'This bike is great and has a nice color', 'Great bike', 3);
INSERT INTO review (id, content, title, product_id)
VALUES (4, 'This shoes are great and comfortable', 'Great shoes', 4);
INSERT INTO review (id, title, content, product_id)
VALUES (5, 'BAD BOOK', 'I did not like the end', 1);

-- comment
INSERT INTO comment (content, review_id)
VALUES ('YES This book is great', 1);
INSERT INTO comment (content, review_id)
VALUES ('YES This computer is fast', 2);
INSERT INTO comment (content, review_id)
VALUES ('NO This bike is not great', 3);
INSERT INTO comment (content, review_id)
VALUES ('YES This shoes are great', 4);
INSERT INTO comment (content, review_id)
VALUES('My comment is that the end is good', 5);
INSERT INTO comment(content, review_id)
VALUES('My comment is that the author should have give a more dramatic end to the story', 5);