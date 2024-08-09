import React from 'react';
import { Link } from 'react-router-dom';
import '../css/Home.css';

const Home = () => {
    const cars = [
        { title: '', image: '/volvos60.jpg' },
        { title: '', image: '/VolkswagenGolf.jpg'},
        { title: '', image: '/FordMustang.jpg' },
        { title: '', image: '/FordTransit.jpg' },
    ];

    return (
        <div className="home-background">
            <div className="cars-container">
                {cars.map((car, index) => (
                    <div key={index} className="car-card">
                        <img src={car.image} alt={car.title} />
                        <h2>{car.title}</h2>
                    </div>
                ))}
            </div>
            <div className="button-container">
                <Link to="/rent" className="car-button">RENT NOW!</Link>
            </div>
        </div>
    );
};

export default Home;