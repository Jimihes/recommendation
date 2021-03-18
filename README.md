# Introduction
This code is written for the Programming for Managers elective at Rotterdam School of Management. The code serves to create a search and auction platform for real estate, where buyers and sellers are able to place and accept bids. The stakeholders involved are an admin, buyers, sellers and guest users. 

Upon initiating the code, each user is required to log-in, afterwhich the code identifies the type of user in accordance to the stakeholders. 

After logging in, the user is presented with several methods which allow the user to select a preferred action. The recommendation.java program allow these actions to occur. Other mentionend programs support it by formulating the methods.

# Getting started
## Prerequisites

Javascript

## Installation

To install the packge and classes in your terminal, run the following:

'''
package recommendation;
import java.util.Scanner;
import java.io.*;
'''

## Usage

### admin.java

This program includes methods which the admin can perform such as deleting a user.

### bidding.java

This program includes methods where the seller can retrieve an array of biddings placed by the buyers.

### buyer.java

This program includes methods where the buyer can insert a bidding for a specific house. In addition, the buyer is able to select a search criteria to find a specific set of houses.

### house.java

This program includes methods where the textfile for houses can be altered (append, overwrite).

### recommendation.java

This program constitutes the main code. The code requires the user to log-in, which allows the program to identify the type of user (admin, buyer, seller or guest user). After identifying, the user is presented several stakeholder-specific methods.

### seller.java

This program includes methods which allow the seller to provide input with regards to listing a house upon the platform. In addition, the seller is able to evaluate the buyer's bidding and decide whether to accept/decline the offer.

### user.java

This program is similar to seller.java, yet calculates the manhatten distance to construct a recommendation system for the buyer after inserting a search criteria (see buyer.java).

# Authors

**Johann Walendzik (451978), Sarah Tappel (444428), Lawrence Mulders (424233), Jimi Heshusius(549127) & Eva de Jong (431165)**
