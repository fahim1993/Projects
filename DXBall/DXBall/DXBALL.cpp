# include "iGraphics.h"
# include <stdio.h>
int x, y, ball_x, ball_y, dx, dy, xl, xr, score, tmp, bricks[30], i, c, strt, clr[30][3], by, slck, dwn;
char scr[10];


void start() {

	for (i = 0; i < 30; i++){
		bricks[i] = 1;
	}
	clr[4][0] = 255; clr[4][1] = 100; clr[4][2] = 100;
	clr[3][0] = 255; clr[3][1] = 50; clr[3][2] = 50;
	clr[2][0] = 235; clr[2][1] = 0; clr[2][2] = 0;
	clr[1][0] = 205; clr[1][1] = 0; clr[1][2] = 0;
	clr[0][0] = 175; clr[0][1] = 0; clr[0][2] = 0;
	
	clr[5][0] = 255; clr[5][1] = 100; clr[5][2] = 100;
	clr[6][0] = 255; clr[6][1] = 50; clr[6][2] = 50;
	clr[7][0] = 235; clr[7][1] = 0; clr[7][2] = 0;
	clr[8][0] = 205; clr[8][1] = 0; clr[8][2] = 0;
	clr[9][0] = 175; clr[9][1] = 0; clr[9][2] = 0;


	by = 420;
	slck = 0;
	score = 0;
	dwn = 1;
	strt = 0;
	scr[0] = 'S'; scr[1] = 'C'; scr[2] = 'O'; scr[3] = 'R'; scr[4] = 'E'; scr[5] = ':'; scr[6] = ' '; scr[7] = ' '; scr[8] = ' ';
	iPauseTimer(0);
	x = 220, y = 50;
	dx = 0; dy = 3;
	ball_x = 244;
	ball_y = 64;
	


}


void drawRect(int p, int q){
	iSetColor(51, 50, 255);
	iFilledRectangle(p, q, 60, 10);
	iSetColor(153,153,255);
	iFilledRectangle(p+20, q, 20, 10);
}


void iDraw()
{

	iClear();
	iSetColor(255,255,255);
	iFilledRectangle(0,480,500,20);
	iSetColor(0,0,0);
	if (score < 10){ 
		
		scr[7] = score+'0'; 
	}
	else {
		
		scr[8] = (score % 10) + '0';
		scr[7] = (score / 10) + '0';
		
	}
	iText(450, 486, scr, GLUT_BITMAP_TIMES_ROMAN_10);
	iSetColor(255, 255, 255);
	if (score == 30){
		iText(225, 240, "YOU WON!", GLUT_BITMAP_TIMES_ROMAN_10);
		iPauseTimer(0);
		
		

	}
	if (ball_y <= 24){
		iText(225, 240, "YOU LOST!", GLUT_BITMAP_TIMES_ROMAN_10);
		iPauseTimer(0);
		
	}
	
	for (i = 0; i < 30;i++){
		
		if (bricks[i] == 1){
			if (i<10){
				iSetColor(clr[i][0], clr[i][1], clr[i][2]);
				iFilledRectangle((i*50)+1, by+1, 48, 18);
			}
			else if (i>=10 && i<20){
				iSetColor(clr[i-10][0]-30, clr[i-10][1]-30, clr[i-10][2]-30);
				iFilledRectangle(((i-10) * 50) + 1, by+21, 48, 18);
			}
			else {
				iSetColor(clr[i-20][0]-60, clr[i-20][1]-60, clr[i-20][2]-60);
				iFilledRectangle(((i-20) * 50) + 1, by+41, 48, 18);
			}
		}
	}

	iSetColor(255, 0, 255);
	iFilledCircle(ball_x, ball_y, 4);
	iSetColor(100,100,255);
	iFilledRectangle(0, 0, 500, 20);
	iSetColor(0, 0 , 255);
	iFilledRectangle(40, 481, 80, 18);
	iSetColor(255,255,255);
	iText(35, 6, "INSTRUCTIONS: CLICK to start, 'P' to pause, 'R' to resume, DRAG THE MOUSE to move the slider ", GLUT_BITMAP_TIMES_ROMAN_10);
	iText(55, 486, "NEW GAME", GLUT_BITMAP_TIMES_ROMAN_10);
	drawRect(x,y);

	
}


void iMouseMove(int mx, int my)
{
	if ((mx >= 40 && mx <= 120) && (my >= 481 && my <= 499)) {
		start();
		return;
	}
	if (strt == 0)return;
	if (mx < 30){
		x = 0;
		xl = 0; xr = 60;
		return;
	}
	else if (mx > 470){
		x = 440;
		xl = 440; xr = 500;
		return;
	}
	else
	{
		x = mx - 30;
		xl = x;
		xr = x + 60;
	}
	
}


void iMouse(int button, int state, int mx, int my)
{
	if (button == GLUT_LEFT_BUTTON && state == GLUT_DOWN){
		if ((mx>=40 && mx<=120) && (my>=481 && my<=499)) {
			start();
			return ;
		}
		if (strt == 0){
			iResumeTimer(0);
			strt = 1;
			return;
		}
		if (mx < 30){
			x = 0;
			xl = 0; xr = 60;
			return;
		}
		else if (mx > 470){
			x = 440;
			xl = 440; xr = 500;
			return;
		}
		else
		{
			x = mx - 30;
			xl = x;
			xr = x + 60;
		}

	}
}


void iKeyboard(unsigned char key)
{
	if (key == 'x')
	{
		exit(0);
	}
	if (key == 'p')
	{
		iPauseTimer(0);
	}
	if (key == 'r')
	{
		iResumeTimer(0);
	}
}


void iSpecialKeyboard(unsigned char key)
{

	if (key == GLUT_KEY_END)
	{
		exit(0);
	}

}

void ballChange(){
	
	if (ball_y >= 477){ 
		dy = -dy; 
		ball_y += dy;
		return; 
	}
	if (ball_x > 496 || ball_x < 4){
		dx = -dx;
		ball_x += dx;
		return;
	}
	ball_x += dx;
	ball_y += dy;
	
	if (dy > 0){
		if (ball_y >= by - 3 && ball_y <= by ){
			c = ball_x / 50;
			if (bricks[c] == 1){
				score++;
				bricks[c] = 0;
				dy = -dy;
				return;

			}	
		}
		else if (ball_y >= by + 17 && ball_y <= by + 20){
			c = ball_x / 50;
			c += 10;
			if (bricks[c] == 1){
				score++;
				bricks[c] = 0;
				dy = -dy;
				return;
			}
		}
		else if (ball_y >= by + 37 && ball_y <= by + 40){
			c = ball_x / 50;
			c += 20;
			if (bricks[c] == 1){
				score++;
				bricks[c] = 0;
				dy = -dy;
				return;

			}
		}
	}
	else if (dy < 0) {
		if (ball_y <= by +63 && ball_y >= by + 60){
			c = ball_x / 50;
			c += 20;
			if (bricks[c] == 1){
				score++;
				bricks[c] = 0;
				dy = -dy;
				return;

			}
		}
		else if (ball_y <= by + 43 && ball_y >= by + 40){
			c = ball_x / 50;
			c += 10;
			if (bricks[c] == 1){
				score++;
				bricks[c] = 0;
				dy = -dy;
				return;

				
			}
		}
		else if (ball_y <= by + 23 && ball_y >= by + 20){
			c = ball_x / 50;
			if (bricks[c] == 1){
				score++;
				bricks[c] = 0;
				dy = -dy;
				return;
			}
		}
	}
	if (ball_y > by && ball_y<by+20){
		c = ball_x / 50;
		if (bricks[c] == 1){
			score++;
			bricks[c] = 0;
			dx = -dx;
			return;
		}
	}
	else if (ball_y > by + 20 && ball_y < by + 40){
		c = ball_x / 50;
		c += 10;
		if (bricks[c] == 1){
			score++;
			bricks[c] = 0;
			dx = -dx;
			return;
		}
	}
	else if (ball_y > by + 40 && ball_y < by + 60){
		c = ball_x / 50;
		c += 20;
		if (bricks[c] == 1){
			score++;
			bricks[c] = 0;
			dx = -dx;
			return;
		}
	}
	
	
	
	if (ball_y < 62 && ball_y> 56 && ball_x >= xl && ball_x <= xr){
		if (dwn % 4 == 0 && dwn<49) {
			by -= 20;
		}
		dwn++;
		if (ball_x <= xl + 20){ dy = 3; dx = -3; }
		else if (ball_x <= xl + 40){ dx = 0; dy = 4; }
		else { dy = 3; dx = 3; }
	}
	
}


int main()
{
	
	iSetTimer(15, ballChange);
	start();
	iInitialize(500, 500, "DXBALL");
	return 0;
}