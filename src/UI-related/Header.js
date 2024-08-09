import React from 'react';
import { Link } from 'react-router-dom';
import '../css/Header.css';

const Header = () => {
    return (
        <header className="header">
            <div className="logo-container">
                <img src="/ACarRental.jpg" alt="Logo" className="logo-image" />
                
            </div>
            <nav>
                <ul className="nav-links">
                    <li><Link to="/">Home</Link></li>
                    <li><Link to="/about">About</Link></li>
                    <li><Link to="/contact">Contact</Link></li>
                    <li><Link to="/rent">Rent a Car</Link></li>
                    <li><Link to="/admin">Admin</Link></li>
                </ul>
            </nav>
        </header>
    );
};

export default Header;