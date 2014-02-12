/*
	Program: dicegame.cpp
	Author: Daniel Gruszczynski
	ITCS 1212 Lab Section: 04
	Date: November 7, 2013

	Purpose: This program simulates a primitive version of the modern dice game Farkle
		     for two players, allowing the users to: enter their names, play the game 
			 until a winner is declared and play as many games as they wish until they
			 decide to quit. 

*/

#include <iostream>
#include <iomanip>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;


/*<--------------------Player  class---------------------> 

	The Player class controls the aspects of the game
	relating to the user, such as: storing their name, keeping 
	track of the points they earn during a turn and the game,
	and updating flags that allows the program to determine:
	if the user has won the game, if it is the user's turn, if
	the user farkled on a dice roll, and which scoring
	combinations are valid based on their roll.

----------------------------------------------------------*/
class Player 
{
	private:
		string name;
		int totalPoints;
		int turnPoints;
		bool isWinner;
		bool isTurn;
		bool isFarkled;
		bool isOption[10];

	public:
		// Constructor 
		Player(string pName, bool option[], int size);

		// Accessors
		string getName();
		int getTotalPoints();
		int getTurnPoints();
		bool getIsWinner();
		bool getIsTurn();
		bool getIsFarkled();
		bool getIsOption(int index);

		// Mutators
		void setName(string pName);
		void setTotalPoints(int points);
		void setTurnPoints(int points);
		void setIsWinner(bool winner);
		void setIsTurn(bool turn);
		void setIsFarkled(bool farkle);
		void setIsOption(bool option[], int size); 
		void setIsOption(int index, bool value);   //overload

		//Other methods
		bool checkIfFarkled();
		int calcPoints(int points);

};

/*-----------------Constructor method--------------------- 

	Purpose: Creates a new player and sets the default
			 values for the fields. 

	Parameters: User-entered name, the default array for
				scoring options, and the array size.
	
---------------------------------------------------------*/
Player::Player(string pName, bool option[], int size)
{
	setName(pName);
	totalPoints = 0;
	turnPoints = 0;
	isWinner = false;	
	isTurn = false;
	isFarkled = false;
	setIsOption(option, size);
}

/*-------------Accessor method definitions----------------

	Purpose: To return a field value of a player

	Parameters: None (except for the getIsOption() method, 
				which requires an index value because
				the method returns only one value from
				the isOption array)

	Return: The field value

--------------------------------------------------------*/
string Player::getName(){return name;}

int Player::getTotalPoints(){return totalPoints;}

int Player::getTurnPoints(){return turnPoints;}

bool Player::getIsWinner(){return isWinner;}

bool Player::getIsTurn(){return isTurn;}
	
bool Player::getIsFarkled(){return isFarkled;}

bool Player::getIsOption(int index){return isOption[index];}

/*--------------Mutator method definitions----------------

	Purpose: To update a field value of a player

	Parameters: The new value that will replace what is
				currently stored in the field.

	Return: None 

---------------------------------------------------------*/
void Player::setName(string pName){name = pName;}

void Player::setTotalPoints(int points){totalPoints += points;}

void Player::setTurnPoints(int points)
{
	if (points == 0)
		turnPoints = 0;
	else
		turnPoints += points;
}

void Player::setIsWinner(bool winner){isWinner = winner;}

void Player::setIsTurn(bool turn){isTurn = turn;}

void Player::setIsFarkled(bool farkle){isFarkled = farkle;}

// Used to set the default values for the isOption array
void Player::setIsOption(bool option[], int size)
{
	for (int i = 0; i < size; i++)
	{
		isOption[i] = option[i];
	}
}

// Overloaded method that replaces just one value from the isOption array 
// Used while player's turn is in progress 
void Player::setIsOption(int index, bool value){isOption[index] = value;}

/*---------------Other Method definitions------------------

	Function: checkIfFarkled
	Purpose:  The function determines if a player has
			  farkled on their turn based on if any of 
			  the eight scoring options are possible.
			  If so, then the player has not farkled
			  and the function returns false. Else,
			  the function returns true. 

	Return:   True - farkled
			  False - not farkled


	Function: calcPoints
	Purpose:  Provides a wrapper for setting turn points.
			  It returns the input (number of points)
			  as is.

	Return:   points
----------------------------------------------------------*/
bool Player::checkIfFarkled()
{
	for (int i = 0; i < 8; i++)
	{
		if(isOption[i] == true)
		{
			return false;
			break;
		}
	}
	return true; 
}

int Player::calcPoints(int points)
{
	return points;
}
	
/*<---------------------Dice  class---------------------->

	The Dice class controls the functionality of the 
	dice during a player's turn. The dice array stores
	the values of the current roll, the diceCount array
	stores how many of a number are stored in the dice 
	array, and diceLeft determines how many dice are still
	in play.

----------------------------------------------------------*/ 
class Dice
{
	private:
		int dice[6];	  // Stores the dice roll (0s are given to dice out of play)
		int diceCount[7]; // How many 0s, 1s, 2s, 3s, etc. rolled (0 - 6)
		int diceLeft;     // How many dice values are not 0

	public:
		// Constructors
		Dice();
		Dice(int count);

		// Accessors
		int getDice(int index);
		int getDiceCount(int index);
		int getDiceLeft();

		// Mutators
		void setDice();
		void setDice(int value, int count); //overload
		void setDiceCount();
		void setDiceLeft();
};

/*-----------------Constructor method---------------------

	Purpose: To create a set of dice to simulate a dice 
			 roll.

	Parameters: The default constructor assumes that there
				are six dice, thus needing no parameters.

				The second constructor needs the number of
				dice to simulate a roll again after a player
				has set aside some dice for scoring.
				
----------------------------------------------------------*/ 
Dice::Dice()
{
	diceLeft = 6;
	setDice();
	setDiceCount();
	
}

Dice::Dice(int count)
{
	diceLeft = count;
	setDice();
	setDiceCount();
}

/*-------------Accessor method definitions----------------

	Purpose: To return a field value.

	Parameters: The index for an array field. None for
				the getDiceLeft() method.

	Return: The field value.  

----------------------------------------------------------*/
int Dice::getDice(int index){return dice[index];}

int Dice::getDiceCount(int index){return diceCount[index];}

int Dice::getDiceLeft(){return diceLeft;}

/*--------------Mutator method definitions----------------

	Purpose: To update field values.

	Parameters: The only method with parameters is the
				overloaded setDice() method. This method
				requires the dice value and the number of
				dice to set aside for scoring. 

	Return: None

----------------------------------------------------------*/
// For regular dice rolls
void Dice::setDice()
{
	for (int i = 0; i < 6; i++) 
	{
		if (i < diceLeft)
			dice[i] = rand() % 6 + 1;
		else
			dice[i] = 0;
	}
}

// Update dice after setting some aside
void Dice::setDice(int value, int count)
{
	for (int i = 0; i < 6; i++)
	{
		if (count == 0)
			break;
		else
		{
			if (dice[i] == value)
			{
				dice[i] = 0;
				count--;
			}
		}
	}
}

// Updates number of each dice value
void Dice::setDiceCount()
{
	for (int i = 0; i < 7; i++)
	{
		int count = 0;

		for (int j = 0; j < 6; j++)
		{
			if (dice[j] == i)
				count++;
		}
		diceCount[i] = count;

	}
}

// Updates number of dice not set aside 
void Dice::setDiceLeft()
{
	diceLeft = 6 - diceCount[0];
}


/*<---------------------Menu  class----------------------> 

	The Menu class controls the scoring options menu
	that displays on the screen for the user. 

----------------------------------------------------------*/
class Menu
{
	private:
		string options[10];
		int points[10];

	public:
		// Constructor
		Menu(string option[], int point[], int index);

		// Accessors
		string getOptions(int index);
		int getPoints(int index);

		// Mutators
		void setOptions(string options[], int index);
		void setPoints(int points[], int index);

		// Other methods
		void displayMenu();

};

/*-----------------Constructor method--------------------- 
	Purpose: Builds the scoring menu 

	Parameters: The default option descriptions (string)
				and its corresponding point value arrays
				along with the size of both arrays.

---------------------------------------------------------*/
Menu::Menu(string option[], int point[], int index)
{
	setOptions(option, index);
	setPoints(point, index);
}


/*-------------Accessor method definitions----------------
	Purpose: Return a field value of the menu
	
	Parameters: The index of the array

	Return: The field value (of a specific description
			or a point value)

---------------------------------------------------------*/
string Menu::getOptions(int index){return options[index];}

int Menu::getPoints(int index){return points[index];}


/*--------------Mutator method definitions----------------
	Purpose: Update a field value

	Parameters: An array and its size. This array should
				be the size of the current array field
				of the menu, whether it be the option 
				description or point values array.

	Return: None

----------------------------------------------------------*/	
void Menu::setOptions(string option[], int index)
{
	for (int i = 0; i < index; i++)
		options[i] = option[i];
}

void Menu::setPoints(int point[], int index)
{
	for (int i = 0; i < index; i++)
	{
		points[i] = point[i];
	}
}

/*---------------Other Method definitions-----------------
	Function: displayMenu

	Purpose: Display the menu onto the screen for the user
			 to read.

	Return: None

----------------------------------------------------------*/
void Menu::displayMenu()
{
	// <----------------- Score Menu ------------------------>
	cout << "Option    Dice Combination         Score" << endl;
	cout << "-----------------------------------------" << endl;

	for (int i = 0; i < 10; i++)
	{
		cout.width(10);
		cout << left << i + 1;
		cout.width(25);
		cout << options[i] << points[i] << endl;
	}

}



// ------------------------------------------- MAIN STARTS HERE --------------------------------------------

// Function prototypes
bool isTurn(Player &, Menu &, int &highScore);
void updateOpt(Player &, Dice &);
void printDice(Dice &);

int main()
{
	// Set seed for pseudo-random number generator
	srand(time(0));

	// <---------------------- Define option defaults for menu and scoring -------------------------------------->
	string options[10] = {"One '1' rolled", "One '5' rolled", "Three 1s", "Three 2s", "Three 3s", "Three 4s", "Three 5s", "Three 6s", "Roll Again", "End Turn"}; 
	int points[10] = {100, 50, 1000, 200, 300, 400, 500, 600, 0, 0};
	bool isOption[10] = {false, false, false, false, false, false, false, false, true, true};


	// Get each player's name
	string name1, name2; 

	cout << "Player 1: What is your name? ";
	cin >> name1;

	cout << "Player 2: What is your name? ";
	cin >> name2;
	
	cout << endl;

	// Create the players
	Player player1(name1, isOption, 10);
	Player player2(name2, isOption, 10);

	// Create menu
	Menu menu(options, points, 10);

	int highScore = 10000;

	//<--------------------------- Main loop begins here -----------------------------> 
	char quit;
	do
	{
		// First to reach 10,000 points
		int round = 0;
		do
		{
			cout << "\nThis is the beginning of round " << round + 1 << "." << endl;			
			
			player1.setIsTurn(true);
			player1.setIsTurn(isTurn(player1, menu, highScore));

			if (player1.getIsWinner())
				break;

			player2.setIsTurn(true);
			player2.setIsTurn(isTurn(player2, menu, highScore));
			round++;

			cout << "This is the end of round " << round << endl;
			cout << player1.getName() << "'s total points: " << player1.getTotalPoints() << endl;
			cout << player2.getName() << "'s total points: " << player2.getTotalPoints() << endl;
		}
		while(player1.getIsWinner() == false && player2.getIsWinner() == false);


		// Give other player one last turn to beat high score 
		if(player1.getIsWinner() && round != 1)
		{
			player2.setIsTurn(true);
			player2.setIsTurn(isTurn(player2, menu, highScore));
		}		
		else 
		{
			player1.setIsTurn(true);
			player1.setIsTurn(isTurn(player1, menu, highScore));
		}
	
		// Game is over --- output results to screen 
		cout << "Game over! ";

		if(player1.getIsWinner())
			cout << "The winner is " << player1.getName() << endl;
		else
			cout << "The winner is " << player2.getName() << endl;

		cout << "Final Scores: " << endl;
		cout << player1.getName() << " : " << player1.getTotalPoints() << " points." << endl;
		cout << player2.getName() << " : " << player2.getTotalPoints() << " points." << endl;
		
		// Prompt play again
		cout << "Would you like to play again? Press 'q' to quit and any other character to continue: ";
		cin >> quit;

	}
	while(!(quit == 'q' || quit == 'Q')); // Keep game running until user decides to quit

	return 0;
}

/*---------------------------------------------------------	
	Function: isTurn

	Purpose: Controls a user's entire turn.
	
	Parameters: Aliases for the specific player,
				the menu object, and the high score
				counter 

	Return: True - still player's turn
			False - player's turn ended 


	Algorithm:

	If start of player's turn:
		- Reset counters and  number of turn points
			 For each new roll:
					1) Create dice
					2) Display menu
					3) Update user options
					4) Check if user has farkled. If not:
					----loop until roll again, hot dice, or turn ends----
						- get valid option
						- add to score
						- update dice
						- display menu
						- update user options 
					
---------------------------------------------------------*/
bool isTurn(Player &player, Menu &menu, int &highScore)
{
		if(player.getIsTurn())
		{
			bool rollAgain = false;
			int numDice = 6;
			player.setTurnPoints(0);
		
			cout << "It is " << player.getName() << "'s turn." << endl;

			do{
				Dice dice1(numDice); // create dice object
				menu.displayMenu();  // display menu
				rollAgain = false;
				bool didPickOnce = false;    // Makes sure user selects one point option before rolling again

				updateOpt(player, dice1); // update player options

				// check if player has farkled 	
				player.setIsFarkled(player.checkIfFarkled());
				if (player.getIsFarkled())
				{
					printDice(dice1);
					cout << "\nYou have farkled. No points for you this turn." << endl;
					player.setTurnPoints(0);
					break;
				}

				opt:	
				int choice = 0;
				while (choice != 9)
				{	
					// Check for hot dice 
					if (dice1.getDiceLeft() == 0)
					{
						cout << "Hot dice! You get to roll again.\n" << endl;
						rollAgain = true;
						numDice = 6;
						break;
					}
					
					// Output dice roll	
					printDice(dice1);
	
					//Get user entered option
					do
					{
						cout << "Which option do you choose? ";
						cin >> choice;
						cout << "You've chosen the option: " << menu.getOptions(choice - 1) << endl << endl;
					}
					while((choice < 0 || choice > 10) || (!player.getIsOption(choice - 1)));
	
					// Adjust points and remove dice
					if (choice < 9)
					{
						didPickOnce = true;
						// First Option 
						if (choice == 1)
						{
							player.setTurnPoints(menu.getPoints(choice -1));
							dice1.setDice(1, 1);
						}
						else
						{
							// Second Option
							if (choice == 2)
							{
								player.setTurnPoints(menu.getPoints(choice -1));
								dice1.setDice(5, 1);
							}
							// Three of a kind
							else
							{
								player.setTurnPoints(menu.getPoints(choice -1));
								dice1.setDice(choice - 2, 3);
							}
						}
	
						dice1.setDiceCount();
						dice1.setDiceLeft();
						numDice = dice1.getDiceLeft();
						cout << player.getName() << " currently has " << player.getTurnPoints() << " points this turn so far." << endl;
						cout << endl;
					}
					else
					{

						// Roll Again 
						if (choice == 9)
						{
							if (didPickOnce)
							{
								rollAgain = true;
								continue;
							}
							else
								cout << "You must set aside at aleast one die before rolling again." << endl;
								goto opt;
						}
						// End turn
						else
						{
							player.setTotalPoints(player.getTurnPoints());
							break;
						}
					}
	
					menu.displayMenu();  // display menu	
					updateOpt(player, dice1); // update player options
				}
			}
			while (rollAgain);

			// Output
			cout << endl;

			cout << player.getName() << " has earned " << player.getTurnPoints() << " points for this turn." << endl;
			cout << player.getName() << "'s total points : " << player.getTotalPoints() << endl;
			cout << endl;

			if (player.getTotalPoints() >= highScore)
			{
				highScore = player.getTotalPoints();
				cout << player.getName() << " has reached the high score of " << highScore << endl;
				player.setIsWinner(true);
			}
			
			return false;
		}
}

/*---------------------------------------------------------
	Function: updateOpt

	Purpose: Update the isOption array field for the current
			 player based on current dice roll.

	Parameters: Alias for player and dice object

	Return: None

----------------------------------------------------------*/
void updateOpt(Player & player, Dice & dice)
{
	// <------ Update options --------->

	if (dice.getDiceCount(1) > 0)
		player.setIsOption(0, true);
	else	
		player.setIsOption(0, false);

	if (dice.getDiceCount(5) > 0)
		player.setIsOption(1, true);
	else	
		player.setIsOption(1, false);

	for(int i = 1; i < 7; i++)
	{
		if (dice.getDiceCount(i) >= 3)
			player.setIsOption(i+1, true);
		else
			player.setIsOption(i+1, false);
	}	
}

/*---------------------------------------------------------
	Function: printDice

	Purpose: Displays current dice roll for all dice 
			 still in play (meaning their value is 
			 greater than zero)

	Parameters: Alias for dice object

	Return: None
----------------------------------------------------------*/

void printDice(Dice & dice)
{
	// Output dice roll 
	cout << "Here is your dice roll: ";
	for (int i = 0; i < 6; i++)
	{
		if( dice.getDice(i) != 0)
			cout << dice.getDice(i) << "  "; 
	}
	cout << endl;
}
