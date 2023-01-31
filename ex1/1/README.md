1. จงเขียนโปรแกรมภาษา Java ทีมีการทํางานดังต่อไปนี ฝัง Server เป็น TCP concurrent server ซึงเมือได้รับ
   การติดต่อจาก client จะส่งข้อความให้ฝัง client ป้อนตัวเลขตัวทีหนึง ถ้า client กด enter โดยไม่ป้อนตัวเลข
   Server จะตัดการติดต่อกับ client ถ้า client ป้อนตัวเลขมา Server จะรับตัวเลข และจะส่งข้อความให้ฝัง
   client ป้อนตัวเลขตัวทีสอง ถ้า client กด enter โดยไม่ป้อนตัวเลข Server จะตัดการติดต่อกับ client ถ้า
   client ป้อนตัวเลขมา server จะรับตัวเลข และหาผลบวกของตัวเลขทั งสอง และส่งผลลัพธ์กลับไปให้ client
   โดยการทํางานของโปรแกรมแสดงได้ดังนี
   Server
   Waiting for client connection at port number 1667
   Client
   enter number 1 (to end just press enter): 2
   enter number 2 (to end just press enter): 3
   The result is 5
   enter number 1 (to end just press enter):
