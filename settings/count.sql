CREATE DEFINER=`root`@`%` PROCEDURE `buyBook`(IN `book_num` INT,IN `order_number` varchar(255),IN `order_time` varchar(255),OUT `res` INT)
BEGIN
	DECLARE book_id VARCHAR(255);
	DECLARE book_stock INT DEFAULT -1;
	DECLARE book_money FLOAT DEFAULT 0.0;
	START TRANSACTION;
	SELECT bookId INTO book_id from bookpurchase WHERE orderNumber = order_number;
	SELECT bookStock,bookPrice INTO book_stock,book_money FROM book WHERE bookId = book_id and bookdeleted = 0;
	SET book_stock = book_stock - book_num;
	IF book_stock < 0 THEN
		SET res = 0;
		ROLLBACK;
	ELSE
		SET book_money = book_money * book_num;
		UPDATE book SET bookStock = book_stock WHERE bookId = book_id and bookdeleted = 0;
		UPDATE bookpurchase SET num = book_num, `time` = order_time, totalMoney = book_money, paymentType = 1, `status` = 5 WHERE orderNumber = order_number;
		SET res = 1;
		COMMIT;
	END IF;
END



