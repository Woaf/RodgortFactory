# RodgortFactory
This project is just a simple crafting system that tries to simulate the crafting of the weapon Rodgort from GuildWars2.
I made this for one of my assigments at uni, where we learn about more advanced utilities in JAVA.

This project does not use any information from other running applications other than itself whatsoever. 

## Usage
The program works automatically. The number of workers / robots, the sleep time of the guild master's thread, the sleep time of the workers' thread, and the number of final craft items to be made can be set in the *resources/config.txt* file.

## Configuration file
We assume that the *resources/config.txt* file is as the following:
- Number of workers to be initialized. :: Int > 0
- Amount of time to sleep for the guild master. :: Int > 0
- Amount of time to sleep for the workers. :: Int > 0
- Amount of final item to be crafted. :: Int > 0
