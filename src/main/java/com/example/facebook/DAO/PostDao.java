

        package com.exampl.facebook.DAO;


import com.example.facebook.Model.Posts;

import com.example.facebook.Service.Like;


import java.sql.*;

import java.util.ArrayList;

import java.util.List;



public class PostDao implements Like {


    private String Query;


    private Connection connection;


    private PreparedStatement preparedStatement;


    private ResultSet resultSet;



    Posts posts;



    public PostDao(Connection connection){


        this.connection = connection;


    }



    public boolean makePost(Posts posts){


        try {


            preparedStatement = this.connection.prepareStatement("INSERT INTO post(userId, content, timeStamp) VALUES (?,?,?)");

// preparedStatement.setInt(1, posts.getPostid());

            preparedStatement.setString(2, posts.getContent());

// preparedStatement.setDate(3, (Date) posts.getTimeStamp());


            int res = preparedStatement.executeUpdate();

            if (res > 0) return true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }finally {


            try {


                preparedStatement.close();


            }catch (SQLException e){


                e.printStackTrace();


            }


        }


        return false;


    }


    public List<Posts> getAllPosts() throws SQLException{


        List<Posts> posts = new ArrayList<>();

        try {

            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM post"); {

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){

                    this.posts = new Posts();

                    this.posts.setPostId(resultSet.getInt("postId"));

                    this.posts.setContent(resultSet.getString("content"));

// this.posts.setTimeStamp(resultSet.getDate("timeStamp"));

                    posts.add(this.posts);

                }

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return posts;

    }


    @Override

    public boolean insertLike(Posts posts) {

        boolean success = false;


        try(

                PreparedStatement statement = connection.prepareStatement("INSERT INTO likes (userId, postId) VALUES (?,?,?)")

        ){

// statement.setInt(1, posts.getUserId());

            statement.setInt(2, posts.getPostId());

            int result = statement.executeUpdate();

            if (result > 0) {

                success = true;

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return success;

    }

    public int getLikeCount(String postId) throws SQLException{

        int likeCount = 0;

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM likes WHERE postId = ?");{

                statement.setString(1, postId);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()){

                    likeCount = resultSet.getInt(1);


                }


            }


        } catch (SQLException e) {


            System.out.println(e.getMessage());


        }


        return likeCount;


    }


}

