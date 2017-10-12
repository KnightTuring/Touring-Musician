/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.touringmusician;


import android.graphics.Point;
import android.util.Log;

import java.util.Iterator;

public class CircularLinkedList implements Iterable<Point> {

    private class Node {
        Point point;
        Node prev, next;
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        Node()
        {
            this.point=null;
            this.prev=null;
            this.next=null;
        }



    }
    public Node getPrev(Node node)
    {
        return (node.prev);
    }
    public Node getNext(Node node)
    {
        return (node.next);
    }
    Node head;
    int MAX_VALUE;

    public void insertBeginning(Point p) {
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        Node newNode=new Node();
        newNode.point=p;
        newNode.prev=null;
        newNode.next=null;
        if(head==null)
        {
            Log.println(Log.INFO,"Message","Init head node");
            newNode.prev=newNode;
            newNode.next=newNode;
            head=newNode;
        }
        else
        {
            //WORKS!
            Log.println(Log.INFO,"Message","Adding node to LL");
            newNode.next=head;
            newNode.prev=head.prev;

            head.prev=newNode;
            newNode.prev.next=newNode;
            head=newNode;
        }
    }

    private float distanceBetween(Point from, Point to) {
        return (float) Math.sqrt(Math.pow(from.y-to.y, 2) + Math.pow(from.x-to.x, 2));
    }

    public float totalDistance() {
        float total = 0;
        /**
         **
         **  YOUR CODE GOES HERE
         **  DONE!
         **/
        Node startNode=head;
        if(startNode.next.next==head)
        {
            Log.println(Log.INFO,"Message","Only 2 points IF block");
            total+=distanceBetween(startNode.point,startNode.next.point);
            return total;
        }
        total+=distanceBetween(startNode.point,startNode.next.point);
        startNode=startNode.next;
        while(startNode!=head)
        {
            Log.println(Log.INFO,"Message","More than 2 points WHILE block");
            total+=distanceBetween(startNode.point,startNode.next.point);
            startNode=startNode.next;
        }
        return total;
    }

    public void insertNearest(Point p) {
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        Node newNode=new Node();
        newNode.point=p;
        float minDistanceNode=0;
        if(head==null)
        {
            head=newNode;
            head.next=head;
            head.prev=head;
        }
        else
        {
            Node pickNode=head;
            Node finalNode=pickNode;
            minDistanceNode=distanceBetween(newNode.point,pickNode.point);
            do {
                if(minDistanceNode>distanceBetween(pickNode.point,newNode.point))
                {
                    minDistanceNode=distanceBetween(pickNode.point,newNode.point);
                    finalNode=pickNode;
                }
                pickNode=pickNode.next;
                Log.println(Log.INFO,"Message","Searching for minimum distance");
            }while(pickNode!=head);

            newNode.next=finalNode.next;
            finalNode.next.prev=newNode;
            finalNode.next=newNode;
            newNode.prev=finalNode;

        }

    }

    public void insertSmallest(Point p) {
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        Node newNode=new Node();
        newNode.point=p;
        if(head==null)
        {
            head=newNode;
            head.next=head;
            head.prev=head;
        }
        else if(head.next==head)
        {
            newNode.next=head;
            newNode.prev=head.prev;

            head.prev=newNode;
            newNode.prev.next=newNode;
            head=newNode;
        }
        else
        {
            Node stNode=head;
            Node finalNode=null;
            float totalFinalDist=0;
            float distFromPrev=0;
            float distFromNext=0;
            float totalDistIncrease=0;
            float min=99999;
            do
            {
                distFromPrev=distanceBetween(stNode.point,newNode.point);
                distFromNext=distanceBetween(newNode.point,stNode.next.point);
                totalDistIncrease=(distFromNext+distFromPrev)-distanceBetween(stNode.point,stNode.next.point);
                if(totalDistIncrease<min)
                {
                    finalNode=stNode;
                    totalFinalDist=totalDistIncrease;
                    min=totalDistIncrease;
                    Log.println(Log.INFO,"Message","Assigning finalNode insertSmallest()");
                }
                stNode=stNode.next;
                Log.println(Log.INFO,"Message","Check for minimum distance in insertSmallest()");
            }while (stNode!=head);

            newNode.prev=finalNode;
            newNode.next=finalNode.next;
            finalNode.next=newNode;
            finalNode.next.prev=newNode;

        }

    }

    public void reset() {
        head = null;
    }

    private class CircularLinkedListIterator implements Iterator<Point> {

        Node current;

        public CircularLinkedListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Point next() {
            Point toReturn = current.point;
            current = current.next;
            if (current == head) {
                current = null;
            }
            return toReturn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return new CircularLinkedListIterator();
    }


}
