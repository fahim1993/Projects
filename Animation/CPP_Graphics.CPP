#include <graphics.h>
#include <conio.h>
#include <stdlib.h>
#include <dos.h>



int dayStat=0; // if 0 then day, if 1 then night.
int sigStat=0; // if 0 then green, if 1 then red.
int wait=0; // wait = 0 (no one waiting in signal), wait = 1 (human waiting in signal).

class clouds {
private:
	int x, y, size, dir;
	static int count;
public :
	clouds(int ax=0, int ay=0, int sz=50){
		count++;
		x=ax;
		y=ay;
		size=sz;
		dir=count;
	}
	void draw () {
		setcolor(15);
		setfillstyle(SOLID_FILL, 15);
		fillellipse(x+size/15, y, size/2.5, size/2.8);
		fillellipse(x-size/2, y-size/20, size/3, size/3.5 );
		fillellipse(x-size/1.6, y+size/4, size/2.7, size/3.5);
		fillellipse(x-size/7, y+size/4, size/2.4, size/3.5);
		fillellipse(x+size/3.5, y+size/7, size/2.2, size/3.2);
		if (dir%2==0) x+=2;
		else x-=2;
		if (x>750 || x<-100 )dir+=1;
	}
};

int clouds::count=0;


class sun {
private:
	int ax, ay, ar, tmp;
public:
	sun(int x=0, int y=0, int r=50){
		ax=x;
		ay=y;
		ar=r;
		tmp=y;
	}
	void draw () {
		setcolor(14);
		setfillstyle(SOLID_FILL, 14);
		fillellipse(ax, ay, ar, ar);
		setcolor(15);
		line (ax+5+ar, ay, ax+ar+15, ay);
		line (ax-5-ar, ay, ax-15-ar, ay);
		line (ax, ay-5-ar, ax, ay-15-ar);
		line (ax, ay+5+ar, ax, ay+15+ar);
		line (ax+(ar+6)/1.4142, ay+(ar+6)/1.4142, ax+8+(ar+6)/1.4142, ay+8+(ar+6)/1.4142);
		line (ax-(ar+6)/1.4142, ay-(ar+6)/1.4142, ax-8-(ar+6)/1.4142, ay-8-(ar+6)/1.4142);
		line (ax+(ar+6)/1.4142, ay-(ar+6)/1.4142, ax+8+(ar+6)/1.4142, ay-8-(ar+6)/1.4142);
		line (ax-(ar+6)/1.4142, ay+(ar+6)/1.4142, ax-8-(ar+6)/1.4142, ay+8+(ar+6)/1.4142);
		ay+=3;
		if (ay>250){
			dayStat=1;
			ay=tmp;
		}
	}
};

class moon {
private:
	int x, y, size, tmp;
public:
	moon (int ax=0, int ay=0, int sz=45) {
		x=ax;
		y=ay;
		size=sz;
		tmp=y;
	}
	void draw() {
		//assuming moon will show when bk color is BLUE.
		setfillstyle(SOLID_FILL, WHITE);
		fillellipse (x,y,  size, size);
		setfillstyle(SOLID_FILL, 1);
		setcolor(1);
		fillellipse (x-size, y-size, size*2, size*2);
		y-=3;
		if (y<-50){
			dayStat=0;
			y=tmp;
		}
	}
};

class building{
private:
	int x, y, size, color, floor;
	//suitable size of building is 25<size<50.
public:
	building (int ax=0, int ay=0, int sz=0, int col=0, int fl=0) {
		x=ax;
		y=ay;
		size=sz;
		color=col;
		floor=fl;
	}
	void draw() {
		int i, yy=y;
		for (i=0; i<floor; i++){
			setfillstyle(SOLID_FILL, color);
			bar(x-size,yy-12, x+size, yy+12);
			setfillstyle (SOLID_FILL, 8);
			bar(x-size+8, yy-(size/7), x-size+16, yy+(size/7));
			bar(x+size-8, yy-(size/7), x+size-16, yy+(size/7));
			setcolor(8);
			line(x-size+1, yy-11, x+size-1, yy-11);
			yy-=24;
		}
	}
};

class car {
private:
	int x, y , size, color, direction, speed;
public:
	car (int ax, int ay, int sz, int cl, int dir, int spd){
		x=ax; y=ay; size=sz; color=cl; direction=dir; speed=spd;
	}
	void draw(){
		int poly1[8]={x-size/1.2, y-size/4, x-size/1.55, y-size/2.2, x-size/3, y-size/2.2, x-size/6.5, y-size/4 };
		int poly2[8]={x-size/1.34, y-size/3.7, x-size/1.65, y-size/2.4, x-size/2.7, y-size/2.4, x-size/4.4, y-size/3.7};
		setfillstyle(SOLID_FILL, color);
		setcolor(8);
		bar(x-size,y-size/4, x,y);
		line(x-size,y-size/4+1, x, y-size/4+1);
		fillpoly(4, poly1);
		setfillstyle(SOLID_FILL,9);
		fillpoly(4, poly2);
		setfillstyle(SOLID_FILL,1);
		fillellipse(x-size/1.35, y-size/35, size/10, size/10);
		fillellipse(x-size/4, y-size/35, size/10, size/10);
		line(x-size/2, y-size/2.4, x-size/2, y);

		headlight();

		// signal and moving direction
		if(direction==0){
			if(sigStat==1){
				if(x<300 && x+speed+1>300 )x-=speed;
			}
			x+=speed;
		}
		else {
			if(sigStat==1) {
				if(x-size>364 && x-size-speed-1<364)x+=speed;
			}
			x-=speed;
		}
		// reposition of car
		if (x>640+60+size){
			x=-50;
		}
		if (x<-60){
			x=640+size+50;
		}

	}
	// headlight function
	void headlight() {
		if(direction==0) {
			if(dayStat==0){
				setfillstyle(SOLID_FILL, 7);
				setcolor(7);
				sector(x, y-size/8, 90, 270, 7, 5);
			}
			else {
				setfillstyle(SOLID_FILL, 14);
				setcolor(14);
				sector(x, y-size/8, 90, 270, 7, 5);
				sector(x+50, y-10, 90, 270, 45, 15);
			}
		}
		else {
			if(dayStat==0) {
				setfillstyle(SOLID_FILL, 7);
				setcolor(7);
				sector(x-size, y-size/8, 0, 90, 7, 5);
				sector(x-size, y-size/8, 270, 360, 7, 5);
			}
			else {
				setfillstyle(SOLID_FILL, 14);
				setcolor(14);
				sector(x-size, y-size/8, 0, 90, 7, 5);
				sector(x-size, y-size/8, 270, 360, 7, 5);
				sector(x-size-50, y-10,  0, 90, 45, 15);
				sector(x-size-50, y-10, 270, 360, 45, 15);
			}
		}
	}
};


class distance {
private:
	building bld[6];
public:
	distance() {
		bld[0]=building (30,260, 29, 6, 3);
		bld[1]=building (150, 260,30,7 , 2 );
		bld[2]=building (240, 260, 35,6, 4 );
		bld[3]=building (330, 260, 30, 12, 3);
		bld[4]=building (450, 260, 37, 7, 2);
		bld[5]=building (580, 260, 40, 11, 5);
	}
	void draw(){
		setcolor(2);
		setfillstyle(SOLID_FILL, 2);
		bar(0, 247, 640, 272);
		fillellipse(70, 250, 12,12);
		fillellipse(90, 253, 22,22);
		fillellipse(120, 253, 19,19);
		fillellipse(190, 253, 12, 12);
		fillellipse(285,253, 10,10);
		fillellipse(360, 253, 20,20);
		fillellipse(385, 253, 14, 14);
		fillellipse(418, 253, 17,17);
		fillellipse(500, 253, 14,14);
		fillellipse(540, 253, 21,17);
		fillellipse(620, 253, 13,13);

		for (int f=0; f<6; f++) {
			bld[f].draw();
		}
	}
};


class signal {
	int x, y;
	static int sigCnt;
public:
	signal(int ax, int ay){
		x=ax, y=ay;
	}
	void draw() {
		setfillstyle(SOLID_FILL, 1);
		setcolor(1);
		bar(x-10, y-100, x, y);
		bar(x-10, y-90, x-30, y-80);
		bar(x-40, y-96, x-20, y-55);
		sigCnt++;
		if(sigCnt%50==0) {
			if (wait==1) sigStat=1;
			else sigStat=0;
		}

		if(sigStat==0) {
			setfillstyle(SOLID_FILL, 2);
			setcolor(2);
			fillellipse(x-31, y-85, 7, 7);
			setfillstyle(SOLID_FILL, 7);
			setcolor(7);
			fillellipse(x-31, y-65, 7,7);
		}
		else {
			setfillstyle(SOLID_FILL, 7);
			setcolor(7);
			fillellipse(x-31, y-85, 7, 7);
			setfillstyle(SOLID_FILL, 4);
			setcolor(4);
			fillellipse(x-31, y-65, 7,7);
		}
	}
};
int signal::sigCnt=0;


class streetlamp{
	int x, y;
public:
	streetlamp(int ax=0, int ay=0){
		x=ax;
		y=ay;
	}
	void draw(){
		setfillstyle(SOLID_FILL, 1);
		setcolor(1);
		bar(x, y+5, x-10, y+12);
		bar(x-19, y, x-10, y+20);

		if (dayStat==0){
			setfillstyle(SOLID_FILL,7);
			setcolor(7);
			fillellipse(x-15, y+20, 7, 7);
		}
		else {
			setfillstyle(SOLID_FILL, 14);
			setcolor(14);
			fillellipse(x-15, y+20, 7,7);
			fillellipse(x-20, y+58, 25, 10);
		}
		setfillstyle(SOLID_FILL, 1);
		setcolor(1);
		bar(x, y, x+7, y+65);
	}
};

class human {
int x, y, size, tmpsz;
public:
	human(int ax, int ay, int sz){
		x=ax;
		y=ay;
		size=sz;
		tmpsz=sz;

	}
	void draw() {
		setfillstyle(SOLID_FILL, 4);
		setcolor(4);
		fillellipse(x, y, size/2, size);
		setfillstyle(SOLID_FILL, 1);
		setcolor(1);
		sector(x, y+size/2.6, 180, 360, size/2, size/1.4);
		bar(x-size/3.5,y+size/1.4,x-2,y+size+size  );
		bar(x+2, y+size/1.4, x+(size/3.5+.8), y+size+size);
		setfillstyle(SOLID_FILL, 6);
		setcolor(6);
		fillellipse(x, y-size, size/2.5, size/2);

		if (x>330 && x<333){
			if (wait==0) wait=1;
			if (sigStat==1){
				y-=3;
				if(y%15==0)size--;
			}
			if(y<270) {
				x+=2;
				wait=0;
			}
		}
		else {
			x+=2;
		}

		if (x>670) {
			x=-30;
			y=399;
			size=tmpsz;
		}
	}
};

class sky{
	clouds c1, c2, c3, c4, c5 ;
	sun SUN;
	moon MOON;
public:
	sky()
	: SUN(450, 2 , 26) , MOON(450, 250, 30)
	{
		c1= clouds (0, 140, 20);
		c2= clouds (550, 110, 60);
		c3= clouds (250, 60, 45);
		c4= clouds (150, 30, 60);
		c5= clouds (550, 30, 40);
	}
	void draw() {
		if (dayStat==0) {
			setfillstyle(SOLID_FILL, 9);
			setcolor(9);
			bar(0,0, 640, 250);
			SUN.draw();
		}
		else {
			setfillstyle(SOLID_FILL, 1);
			setcolor(1);
			bar(0,0, 640, 250);
			putpixel(50, 50, 14);
			putpixel(200, 55, 15);
			putpixel(300, 45, 14);
			putpixel(450, 50, 15);
			putpixel(550, 60, 14);
			putpixel(620, 75, 15);
			putpixel(150, 150, 14);
			putpixel(230, 185, 14);
			putpixel(370, 200, 14);
			putpixel(480, 165, 15);
			putpixel(590, 195, 15);
			putpixel(140, 177, 14);
			putpixel(270, 203, 15);
			putpixel(390, 216, 14);
			putpixel(480, 197, 14);

			MOON.draw();
		}
		c1.draw(); c2.draw(); c3.draw(); c4.draw(); c5.draw();
	}
};

class streets {
	car CAR1, CAR2;
	streetlamp STLAMP1, STLAMP2;
	human HUMAN;
	signal SIGNAL;
public:

	streets ()
	:CAR1(350, 357, 120, 2, 0, 17), CAR2(640, 410, 120, 6, 1, 8), STLAMP1(100, 233), STLAMP2(580, 233), HUMAN(230, 399, 25), SIGNAL(400, 470)
	{}
	void draw(){
		int zebraMrk1[8]={301, 341, 361, 321, 361, 326, 301, 346};
		int zebraMrk2[8]={301, 366, 361, 346, 361, 351, 301, 371};
		int zebraMrk3[8]={301, 391, 361, 371, 361, 376, 301, 396};
		int zebraMrk4[8]={301, 416, 361, 396, 361, 401, 301, 421};
		setcolor(7);
		setfillstyle(SOLID_FILL, 8);
		bar(0, 272, 640, 278);
		bar(0, 321, 640, 421);
		setfillstyle(SOLID_FILL, 7);
		bar(0, 279, 640, 320);
		bar(0, 422, 640, 480);
		setfillstyle(SOLID_FILL, 15);
		bar(20, 367, 120, 375);
		bar(170, 367, 270, 375);
		bar(400, 367, 500, 375 );
		bar(550, 367, 640, 375);
		setcolor(15);
		bar(300, 321, 304, 421); // zebra
		bar(360, 321, 364, 421);
		fillpoly(4,zebraMrk1);
		fillpoly(4,zebraMrk2);
		fillpoly(4,zebraMrk3);
		fillpoly(4,zebraMrk4);
		// streetlight
		setcolor(1);
		setfillstyle(SOLID_FILL, 1);

		STLAMP1.draw();
		CAR1.draw();
		CAR2.draw();
		HUMAN.draw();
		STLAMP2.draw();
		SIGNAL.draw();
	}
};

class SCREEN_PLAY{
	sky SKY;
	distance DISTANCE;
	streets STREETS;
public:
	void play() {
		setbkcolor(9);
		while(!kbhit()){
			SKY.draw();
			DISTANCE.draw();
			STREETS.draw();
			delay(400);
		}
	}
};

void main () {

	int driver=DETECT, mode, i;
	initgraph (&driver, &mode, "c:\\turboc3\\bgi");
	SCREEN_PLAY P1;
	P1.play();
}