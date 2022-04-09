INSERT INTO consumer (consumer_id, id, name, pwd) VALUES (1, '1234', '홍길동', '55555');
INSERT INTO post (id, consumer_id) VALUES (1, 1);
INSERT INTO Likes (id, consumer_id, post_id) VALUES (1, 1, 1);
INSERT INTO Seller (seller_id, name, s_Id, store_Name, IS_APPROVED ) values (1, '판매자1', '1234', '내일의 집', TRUE);
INSERT INTO Seller (seller_id, name, s_Id, store_Name, IS_APPROVED ) values (2, '판매자2', '155234', '오늘의 집', FALSE);

select c.ID, c.name, c.point, count(p.id) as post_num, count(l.id) as likes_num, count(r.review_id) as review_num from Consumer c, Post p, Likes l, Review r where c.consumer_id = p.consumer_id and p.consumer_id = l.consumer_id and l.consumer_id = r.consumer_id GROUP BY c.consumer_id;